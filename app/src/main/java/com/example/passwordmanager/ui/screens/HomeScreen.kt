package com.example.passwordmanager.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.passwordmanager.R
import com.example.passwordmanager.data.model.PasswordEntry
import com.example.passwordmanager.ui.theme.FABColor
import com.example.passwordmanager.ui.theme.LightGrey
import com.example.passwordmanager.ui.theme.MainBg
import com.example.passwordmanager.ui.theme.SecondaryTextColor
import com.example.passwordmanager.utils.TextStyles
import com.example.passwordmanager.utils.noRippleClick
import com.example.passwordmanager.viewmodel.PasswordViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel:PasswordViewModel = hiltViewModel()
    val passwords by viewModel.passwords.collectAsStateWithLifecycle()

    var showBottomSheet by remember { mutableStateOf(false) }
    var isAddMode by remember { mutableStateOf(true) }
    var isEditMode by remember { mutableStateOf(false) }
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
                    ItemPasswordEntry(
                        entry = entry,
                        onClick = {
                            selectedEntry = entry
                            isAddMode = false
                            showBottomSheet = true
                        }
                    )
                }
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
                isEditMode = false
            },
            sheetState = sheetState,
            containerColor = LightGrey,
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp
            ),
//            dragHandle = null
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                Box(
//                    modifier = Modifier
//                        .height(4.dp)
//                        .widthIn(46.dp)
//                        .padding(vertical = 8.dp)
//                        .background(
//                            color = Color(0xFFE3E3E3),
//                            shape = CircleShape
//                        )
//                )
                if (isAddMode) {
                    AccountSheet(
                        isEditMode = isEditMode,
                        entry = selectedEntry,
                        onSubmit = { account, username, password ->
                            viewModel.addPassword(account, username, password)
                            coroutineScope.launch { sheetState.hide() }
                            showBottomSheet = false
                            isEditMode = false
                        },
                        onUpdateClick = { account, username, password ->
                            val newEntry = selectedEntry?.copy(
                                account = account,
                                username = username
                            )
                            viewModel.updatePassword(newEntry, password)
                            coroutineScope.launch { sheetState.hide() }
                            showBottomSheet = false
                            isEditMode = false
                        }
                    )
                } else {
                    selectedEntry?.let { entry ->
                        AccountDetailsSheet(
                            entry = entry,
                            onEdit = {
                                isAddMode = true
                                isEditMode = true
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
}

@Composable
private fun ItemPasswordEntry(
    entry: PasswordEntry,
    onClick:() -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .background(
                color = Color.White,
                shape = CircleShape
            )
            .border(
                width = 1.dp,
                shape = CircleShape,
                color = Color(0xFFEDEDED)
            )
            .padding(
                horizontal = 20.dp
            )
            .noRippleClick {
                onClick.invoke()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = entry.account,
            style = TextStyles.SfProDisplay.semiBold(size = 20),
            modifier = Modifier.widthIn(max = (screenWidth*0.5).dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
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