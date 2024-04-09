package dev.babananick.pap.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.babananick.pap.theme.PAPTypo

@Composable
fun ButtonText(
    modifier: Modifier = Modifier,
    color: Color,
    text: String
){
    Text(
        modifier = modifier,
        text = text,
        color = color,
        style = PAPTypo.navigationButtonTextStyle
    )
}