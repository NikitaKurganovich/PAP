package dev.babananick.pap.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.babananick.pap.ui.components.text.ButtonText


@Composable
fun NavigateFilled(
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
            .background(Color(0xFF31674D), buttonShape),
        contentAlignment = Alignment.Center
    ) {
        ButtonText(
            text = text,
            color = Color(0xFFEEFDEF)
        )
    }
}

val buttonShape: RoundedCornerShape
    @Composable
    get() {
        val shape = RoundedCornerShape(15.dp)
        return remember {
            shape
        }
    }