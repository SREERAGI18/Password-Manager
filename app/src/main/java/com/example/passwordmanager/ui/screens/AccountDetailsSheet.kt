package com.example.passwordmanager.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.data.model.PasswordEntry
import com.example.passwordmanager.encryption.EncryptionUtils
import com.example.passwordmanager.encryption.EncryptionUtils.decrypt

@Composable
fun AccountDetailsSheet(
    entry: PasswordEntry,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val decryptedPassword = decrypt(entry.encryptedPassword, entry.iv)

    Column(modifier = Modifier.padding(24.dp)) {
        Text("Account Details", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        Text("Account Type", fontWeight = FontWeight.SemiBold)
        Text(entry.account)

        Spacer(Modifier.height(8.dp))
        Text("Username/ Email", fontWeight = FontWeight.SemiBold)
        Text(entry.username)

        Spacer(Modifier.height(8.dp))
        Text("Password", fontWeight = FontWeight.SemiBold)
        Text("••••••••••")

        Row(modifier = Modifier.padding(top = 24.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = onEdit, modifier = Modifier.weight(1f)) {
                Text("Edit")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onDelete, colors = ButtonDefaults.buttonColors(containerColor = Color.Red), modifier = Modifier.weight(1f)) {
                Text("Delete")
            }
        }
    }
}