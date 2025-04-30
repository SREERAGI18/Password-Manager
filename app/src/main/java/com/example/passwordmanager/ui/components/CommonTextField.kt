package com.example.passwordmanager.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.utils.TextStyles

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    value:String,
    labelText: String,
    labelStyle: TextStyle,
    onValueChanged: (String) -> Unit,
    errorMessage:String = ""
) {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = errorMessage,
            style = TextStyles.Roboto.medium(
                size = 12,
                color = Color.Red
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
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
            onValueChange = onValueChanged,
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier.padding(horizontal = 15.dp)
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = labelText,
                            style = labelStyle,
                        )
                    }

                    innerTextField()
                }
            }
        )
    }
}