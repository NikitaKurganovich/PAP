package dev.babananick.pap.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.unit.sp

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val ralewayFontFamily = FontFamily(
    Font(googleFont = GoogleFont("Raleway"), fontProvider = provider)
)

val PAPTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (-0.25).sp,
        hyphens = Hyphens.Auto
    ),
    displayMedium = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (0).sp,
        hyphens = Hyphens.Auto
    ),
    displaySmall = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (0).sp,
        hyphens = Hyphens.Auto
    ),
    headlineLarge = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (0).sp,
        hyphens = Hyphens.Auto
    ),
    headlineMedium = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (0).sp,
        hyphens = Hyphens.Auto
    ),
    headlineSmall = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (0).sp,
        hyphens = Hyphens.Auto
    ),
    titleLarge = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (0).sp,
        hyphens = Hyphens.Auto
    ),
    titleMedium = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = (0.15).sp,
        hyphens = Hyphens.Auto
    ),
    bodyLarge = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (0.5).sp,
        hyphens = Hyphens.Auto
    ),
    bodyMedium = TextStyle(
        fontFamily = ralewayFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W400,
        letterSpacing = (0.25).sp,
        hyphens = Hyphens.Auto
    ),
)
