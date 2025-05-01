package com.example.passwordmanager.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.data.model.PasswordEntry
import com.example.passwordmanager.encryption.EncryptionUtils
import com.example.passwordmanager.encryption.EncryptionUtils.decrypt
import com.example.passwordmanager.ui.theme.FABColor
import com.example.passwordmanager.ui.theme.RedColor
import com.example.passwordmanager.ui.theme.SecondaryTextColor
import com.example.passwordmanager.utils.TextStyles

@Composable
fun AccountDetailsSheet(
    entry: PasswordEntry,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val decryptedPassword = decrypt(entry.encryptedPassword, entry.iv)

    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Text(
            text = "Account Details",
            style = TextStyles.SfProDisplay.semiBold(
                size = 19,
                color = FABColor
            ),
        )
        Spacer(Modifier.height(16.dp))

        Text(
            text = "Account Type",
            style = TextStyles.Roboto.medium(
                size = 12,
                color = SecondaryTextColor
            )
        )
        Text(
            text = entry.account,
            style = TextStyles.Poppins.semiBold(
                size = 16
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(Modifier.height(20.dp))
        Text(
            text = "Username/ Email",
            style = TextStyles.Roboto.medium(
                size = 12,
                color = SecondaryTextColor
            )
        )
        Text(
            text = entry.username,
            style = TextStyles.Poppins.semiBold(
                size = 16
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Password",
                    style = TextStyles.Roboto.medium(
                        size = 12,
                        color = SecondaryTextColor
                    )
                )
                val password = if(passwordVisible) {
                    decryptedPassword
                } else {
                    "••••••••••"
                }
                Text(
                    text = password,
                    style = TextStyles.Poppins.semiBold(
                        size = 16
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            IconButton(
                onClick = { passwordVisible = !passwordVisible },
            ) {
                Icon(
                    imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                )
            }
        }

        Row(
            modifier = Modifier.padding(top = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onEdit,
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black,
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Edit",
                    style = TextStyles.Poppins.bold(size = 16, color = Color.White)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = onDelete,
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = RedColor,
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Delete",
                    style = TextStyles.Poppins.bold(size = 16, color = Color.White)
                )
            }
        }

        Spacer(Modifier.height(20.dp))
    }
}