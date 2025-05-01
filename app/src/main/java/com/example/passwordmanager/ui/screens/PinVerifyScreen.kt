package com.example.passwordmanager.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.passwordmanager.encryption.EncryptedSharedPreferenceManager.verifyPin
import com.example.passwordmanager.navigation.Screens
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun PinVerifyScreen(
    navController: NavController
) {
    val context = LocalContext.current
    var pin by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Enter your 4-digit PIN", fontSize = 18.sp)

        OutlinedTextField(
            value = pin,
            onValueChange = {
                if (it.length <= 4 && it.all { char -> char.isDigit() }) {
                    pin = it
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.NumberPassword),
            isError = error,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.padding(top = 16.dp)
        )

        if (error) {
            Text("Incorrect PIN", color = Color.Red, fontSize = 12.sp)
        }

        Button(
            onClick = {
                if (verifyPin(context, pin)) {
                    error = false
                    navController.navigate(Screens.Home)
                } else {
                    error = true

                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Unlock")
        }
    }
}