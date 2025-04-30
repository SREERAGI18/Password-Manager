package com.example.passwordmanager.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.passwordmanager.ui.theme.MainTextColor


object TextStyles {
    object SfProDisplay {
        fun light(size: Int, color: Color = MainTextColor, alpha: Float = 1f): TextStyle {
            return TextStyle(
                fontSize = size.sp,
//            fontFamily = Albert,
                color = color.copy(
                    alpha = alpha
                ),
                fontWeight = FontWeight.Light
            )
        }

        fun regular(size: Int, color: Color = MainTextColor, alpha: Float = 1f): TextStyle {
            return TextStyle(
                fontSize = size.sp,
//            fontFamily = Albert,
                color = color.copy(
                    alpha = alpha
                ),
                fontWeight = FontWeight.Normal
            )
        }

        fun medium(size: Int, color: Color = MainTextColor, alpha: Float = 1f): TextStyle {
            return TextStyle(
                fontSize = size.sp,
//            fontFamily = Albert,
                color = color.copy(
                    alpha = alpha
                ),
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.sp
            )
        }

        fun semiBold(size: Int, color: Color = MainTextColor, alpha: Float = 1f): TextStyle {
            return TextStyle(
                fontSize = size.sp,
//            fontFamily = Albert,
                color = color.copy(
                    alpha = alpha
                ),
                fontWeight = FontWeight.SemiBold
            )
        }

        fun bold(size: Int, color: Color = MainTextColor, alpha: Float = 100f): TextStyle {
            return TextStyle(
                fontSize = size.sp,
//            fontFamily = Albert,
                color = color.copy(
                    alpha = alpha
                ),
                fontWeight = FontWeight.Bold
            )
        }
    }

    object Poppins {
        fun light(size: Int, color: Color = MainTextColor, alpha: Float = 1f): TextStyle {
            return TextStyle(
                fontSize = size.sp,
//            fontFamily = Albert,
                color = color.copy(
                    alpha = alpha
                ),
                fontWeight = FontWeight.Light
            )
        }

        fun regular(size: Int, color: Color = MainTextColor, alpha: Float = 1f): TextStyle {
            return TextStyle(
                fontSize = size.sp,
//            fontFamily = Albert,
                color = color.copy(
                    alpha = alpha
                ),
                fontWeight = FontWeight.Normal
            )
        }

        fun medium(size: Int, color: Color = MainTextColor, alpha: Float = 1f): TextStyle {
            return TextStyle(
                fontSize = size.sp,
//            fontFamily = Albert,
                color = color.copy(
                    alpha = alpha
                ),
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.sp
            )
        }

        fun semiBold(size: Int, color: Color = MainTextColor, alpha: Float = 1f): TextStyle {
            return TextStyle(
                fontSize = size.sp,
//            fontFamily = Albert,
                color = color.copy(
                    alpha = alpha
                ),
                fontWeight = FontWeight.SemiBold
            )
        }

        fun bold(size: Int, color: Color = MainTextColor, alpha: Float = 100f): TextStyle {
            return TextStyle(
                fontSize = size.sp,
//            fontFamily = Albert,
                color = color.copy(
                    alpha = alpha
                ),
                fontWeight = FontWeight.Bold
            )
        }
    }

    object Roboto {
        fun light(size: Int, color: Color = MainTextColor, alpha: Float = 1f): TextStyle {
            return TextStyle(
                fontSize = size.sp,
//            fontFamily = Albert,
                color = color.copy(
                    alpha = alpha
                ),
                fontWeight = FontWeight.Light
            )
        }

        fun regular(size: Int, color: Color = MainTextColor, alpha: Float = 1f): TextStyle {
            return TextStyle(
                fontSize = size.sp,
//            fontFamily = Albert,
                color = color.copy(
                    alpha = alpha
                ),
                fontWeight = FontWeight.Normal
            )
        }

        fun medium(size: Int, color: Color = MainTextColor, alpha: Float = 1f): TextStyle {
            return TextStyle(
                fontSize = size.sp,
//            fontFamily = Albert,
                color = color.copy(
                    alpha = alpha
                ),
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.sp
            )
        }

        fun semiBold(size: Int, color: Color = MainTextColor, alpha: Float = 1f): TextStyle {
            return TextStyle(
                fontSize = size.sp,
//            fontFamily = Albert,
                color = color.copy(
                    alpha = alpha
                ),
                fontWeight = FontWeight.SemiBold
            )
        }

        fun bold(size: Int, color: Color = MainTextColor, alpha: Float = 100f): TextStyle {
            return TextStyle(
                fontSize = size.sp,
//            fontFamily = Albert,
                color = color.copy(
                    alpha = alpha
                ),
                fontWeight = FontWeight.Bold
            )
        }
    }
}