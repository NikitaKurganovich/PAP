package dev.babananick.pap.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class PAPTypography(
    val questionTextStyle: TextStyle,
    val answerVariantTextStyle: TextStyle,
    val navigationButtonTextStyle: TextStyle,
    val testNameTextStyle: TextStyle,
    val questionQuantityTextStyle: TextStyle,
    val moduleNameTextStyle: TextStyle,
    val navigateTextStyle: TextStyle,
    val headerTextStyle: TextStyle
)

val PAPTypo = PAPTypography(
    questionTextStyle = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    answerVariantTextStyle = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 17.sp,
        letterSpacing = 0.5.sp
    ),
    navigationButtonTextStyle = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 17.sp,
        letterSpacing = 0.5.sp
    ),
    testNameTextStyle = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    questionQuantityTextStyle = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    moduleNameTextStyle = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        color = Color(0xFF31674D)
    ),
    navigateTextStyle = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 23.sp,
        lineHeight = 17.sp,
        letterSpacing = 0.5.sp,
    ),
    headerTextStyle = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
    ),
)