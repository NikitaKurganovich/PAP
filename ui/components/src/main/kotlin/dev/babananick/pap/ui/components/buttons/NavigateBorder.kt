package dev.babananick.pap.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.babananick.pap.ui.components.text.ButtonText

@Composable
fun NavigateBorder(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
    text: String,
) {
    Box(
        modifier = modifier
            .size(height = 45.dp, width = 150.dp)
            .clip(buttonShape)
            .clickable(
                enabled = isEnabled,
                onClick = onClick
            )
            .background(Color.White, buttonShape)
            .border(2.dp, Color(0xFF31674D), buttonShape),
        contentAlignment = Alignment.Center
        ) {
        ButtonText(
            text = text,
            color = Color(0xFF31674D)
        )
    }
}