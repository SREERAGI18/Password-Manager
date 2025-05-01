package com.example.passwordmanager.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.utils.TextStyles
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    value: String,
    labelText: String,
    labelStyle: TextStyle,
    onValueChange: (String) -> Unit,
    errorMessage: String = "",
    isPassword: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.Start
    ) {
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                style = TextStyles.Roboto.medium(
                    size = 12,
                    color = Color.Red
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
        }

        BasicTextField(
            modifier = modifier
                .fillMaxWidth()
                .height(44.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(6.dp)
                )
                .border(
                    brush = SolidColor(Color(0xFFCBCBCB)),
                    width = 0.6.dp,
                    shape = RoundedCornerShape(6.dp)
                ),
            singleLine = true,
            maxLines = 1,
            value = value,
            onValueChange = onValueChange,
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent, shape = CircleShape)
                        .padding(start = 15.dp, end = if(isPassword) 0.dp else 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (value.isEmpty()) {
                            Text(
                                text = labelText,
                                style = labelStyle,
                            )
                        }
                        innerTextField()
                    }

                    if (isPassword) {
                        IconButton(
                            onClick = { passwordVisible = !passwordVisible },
                        ) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (passwordVisible) "Hide password" else "Show password"
                            )
                        }
                    }
                }
            }
        )
    }
}
