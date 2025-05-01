package dev.babananick.pap.ui.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import dev.babananick.pap.ui.theme.ralewayFontFamily

@Composable
fun ButtonText(
    modifier: Modifier = Modifier,
    color: Color,
    text: String,
    fontFamily: FontFamily
){
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontFamily = fontFamily
    )
}