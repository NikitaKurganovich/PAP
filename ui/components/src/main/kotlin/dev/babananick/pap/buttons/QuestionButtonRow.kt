package dev.babananick.pap.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.babananick.pap.ui.theme.Green40
import dev.babananick.pap.ui.theme.Purple80




@Composable
fun NavigatorButton(
    modifier: Modifier = Modifier,
    currentQuestion: Int,
    position: Int,
    onClick: (() -> Unit),
    onSizeMeasured: (Int) -> Unit,
) {
    val currentColor = remember(currentQuestion) {
        if (currentQuestion + 1 == position) Green40
        else Purple80
    }
    val shape by remember { mutableStateOf(RoundedCornerShape(100)) }
    Box(
        modifier = modifier
            .padding(7.dp)
            .background(currentColor, shape)
            .size(48.dp)
            .clip(shape)
            .onGloballyPositioned { coordinates ->
                onSizeMeasured(coordinates.size.width)
            }
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = position.toString(),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 23.sp)
        )
    }
}

@Composable
fun NextAndPrevious(
    isNextEnabled: Boolean,
    isPreviousEnabled: Boolean,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
) {
    Row {
        Navigate(
            isEnabled = isPreviousEnabled,
            onClick = onPrevious,
            text = "To previous"
        )
        Navigate(
            isEnabled = isNextEnabled,
            onClick = onNext,
            text = "To next"
        )
    }
}

@Composable
fun Navigate(
    isEnabled: Boolean,
    onClick: () -> Unit,
    text: String,
) {
    Button(
        enabled = isEnabled,
        onClick = onClick,
        modifier = Modifier.padding(3.dp)
    ) {
        Text(text)
    }
}
