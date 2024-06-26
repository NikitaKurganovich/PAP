package dev.babananick.pap.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NavigatorButtonText(
    modifier: Modifier = Modifier,
    text: String
){
    Text(
        modifier = modifier,
        text = text
    )
}