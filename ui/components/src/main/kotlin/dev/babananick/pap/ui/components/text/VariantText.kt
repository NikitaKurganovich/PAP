package dev.babananick.pap.ui.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.babananick.pap.ui.theme.PAPTypo

@Composable
fun VariantText(
    modifier: Modifier = Modifier,
    variant: String,
    color: Color
) {
    Text(
        modifier = modifier,
        text = variant,
        style = dev.babananick.pap.ui.theme.PAPTypo.answerVariantTextStyle,
        color = color
    )
}