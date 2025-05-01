package com.example.passwordmanager.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.passwordmanager.R

val RobotoFamily = FontFamily(
    Font(R.font.roboto_bold_700, weight = FontWeight.Bold),
    Font(R.font.roboto_medium_500, weight = FontWeight.Medium),
    Font(R.font.roboto_regular_400, weight = FontWeight.Normal)
)

val SfProDisplayFamily = FontFamily(
    Font(R.font.sf_pro_display_bold, weight = FontWeight.Bold),
    Font(R.font.sf_pro_display_medium, weight = FontWeight.Medium),
    Font(R.font.sf_pro_display_regular_400, weight = FontWeight.Normal),
)

val PoppinsFamily = FontFamily(
    Font(R.font.poppins_bold_700, weight = FontWeight.Bold),
    Font(R.font.poppins_medium_500, weight = FontWeight.Medium),
    Font(R.font.poppins_regular_400, weight = FontWeight.Normal),
    Font(R.font.poppins_light_300, weight = FontWeight.Light)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)