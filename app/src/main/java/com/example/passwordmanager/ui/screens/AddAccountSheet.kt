package com.example.passwordmanager.ui.screens

import android.text.TextUtils
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.ui.components.CommonTextField
import com.example.passwordmanager.ui.theme.TextFieldHintColor
import com.example.passwordmanager.utils.TextStyles
import java.util.regex.Matcher
import java.util.regex.Pattern

@Composable
fun AddAccountSheet(onSubmit: (String, String, String) -> Unit) {
    var accountType by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var errorAccountType by remember { mutableStateOf("") }
    var errorUsername by remember { mutableStateOf("") }
    var errorPassword by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        CommonTextField(
            value = accountType,
            onValueChanged = {
                accountType = it
                errorAccountType = ""
            },
            labelText = "Account Type",
            labelStyle = TextStyles.Roboto.medium(size = 13, color = TextFieldHintColor),
            errorMessage = errorAccountType
        )
        Spacer(modifier = Modifier.height(10.dp))
        CommonTextField(
            value = username,
            onValueChanged = {
                username = it
                errorUsername = ""
            },
            labelText = "Username/ Email",
            labelStyle = TextStyles.Roboto.medium(size = 13, color = TextFieldHintColor),
            errorMessage = errorUsername
        )
        Spacer(modifier = Modifier.height(10.dp))
        CommonTextField(
            value = password,
            onValueChanged = {
                password = it
                errorPassword = ""
            },
            labelText = "Password",
            labelStyle = TextStyles.Roboto.medium(size = 13, color = TextFieldHintColor),
            errorMessage = errorPassword
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                errorAccountType = accountType.validateAccountType()
                errorUsername = username.validateUserName()
                errorPassword = password.validatePassword()

                if(
                    errorAccountType.isNotBlank() ||
                    errorUsername.isNotBlank() ||
                    errorPassword.isNotBlank()
                ) {
                    return@Button
                }

                onSubmit(accountType, username, password)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Black,
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "Add New Account",
                style = TextStyles.Poppins.bold(size = 16, color = Color.White)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

private fun String.isValidEmail():Boolean {
    if(trim().isEmpty()) return false

    val pattern: Pattern
    val EMAIL_PATTERN = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}"
    pattern = Pattern.compile(EMAIL_PATTERN)
    val matcher: Matcher = pattern.matcher(this.trim())

    return (!TextUtils.isEmpty(this.trim()) && matcher.matches())
}

private fun String.validateUserName():String {
    return if(contains("@")) {
        if(!isValidEmail()) {
            "Please enter a valid email address."
        } else {
            ""
        }
    } else {
        if(isNotBlank()) {
            ""
        } else {
            "Username cannot be empty."
        }
    }
}

private fun String.validatePassword():String {
    return if(isNotBlank()) {
        ""
    } else {
        "Password cannot be empty."
    }
}

private fun String.validateAccountType():String {
    return if(isNotBlank()) {
        ""
    } else {
        "Account type cannot be empty."
    }
}