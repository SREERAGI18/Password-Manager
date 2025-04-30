package com.example.passwordmanager.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.passwordmanager.R
import com.example.passwordmanager.data.model.PasswordEntry
import com.example.passwordmanager.ui.theme.FABColor
import com.example.passwordmanager.ui.theme.MainBg
import com.example.passwordmanager.ui.theme.SecondaryTextColor
import com.example.passwordmanager.utils.TextStyles
import com.example.passwordmanager.viewmodel.PasswordViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel:PasswordViewModel = hiltViewModel()
    val passwords by viewModel.passwords.collectAsStateWithLifecycle()

    var showBottomSheet by remember { mutableStateOf(false) }
    var isAddMode by remember { mutableStateOf(true) }
    var selectedEntry by remember { mutableStateOf<PasswordEntry?>(null) }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        containerColor = MainBg,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isAddMode = true
                    selectedEntry = null
                    showBottomSheet = true
                },
                containerColor = FABColor,
                contentColor = Color.White,
                modifier = Modifier.size(60.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.add_icon),
                    contentDescription = "Add",
                    modifier = Modifier.size(26.dp),
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Password Manager",
                        style = TextStyles.SfProDisplay.semiBold(size = 18)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MainBg
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            HorizontalDivider(
                color = Color(0xFFE8E8E8),
                thickness = 1.dp
            )
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(passwords) { entry ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(66.dp)
                            .background(
                                color = Color.White,
                                shape = CircleShape
                            )
                            .padding(
                                horizontal = 20.dp
                            )
                            .clickable {
                                selectedEntry = entry
                                isAddMode = false
                                showBottomSheet = true
                            },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = entry.account,
                            style = TextStyles.SfProDisplay.semiBold(size = 20)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "*******",
                            style = TextStyles.SfProDisplay.semiBold(
                                size = 20,
                                color = SecondaryTextColor
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            painter = painterResource(R.drawable.arrow_right),
                            contentDescription = "Arrow Right Icon"
                        )
                    }
                }
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            if (isAddMode) {
                AddAccountSheet { account, username, password ->
                    viewModel.addPassword(account, username, password)
                    coroutineScope.launch { sheetState.hide() }
                    showBottomSheet = false
                }
            } else {
                selectedEntry?.let { entry ->
                    AccountDetailsSheet(
                        entry = entry,
                        onEdit = {
                            isAddMode = true
                        },
                        onDelete = {
                            viewModel.deletePassword(entry)
                            coroutineScope.launch { sheetState.hide() }
                            showBottomSheet = false
                        }
                    )
                }
            }
        }
    }
}