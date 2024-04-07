package dev.babananick.pap.buttons

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.babananick.pap.ui.theme.Green40
import dev.babananick.pap.ui.theme.Purple80




@Composable
fun NavigatorButton(
    currentQuestion: Int,
    position: Int,
    onClick: (() -> Unit),
    onSizeMeasured: (Int) -> Unit,
) {
    val currentColor = remember(currentQuestion) {
        if (currentQuestion + 1 == position) Green40
        else Purple80
    }

    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(3.dp)
            .size(50.dp)
            .onGloballyPositioned { coordinates ->
                onSizeMeasured(coordinates.size.width)
            },
        colors = ButtonDefaults.buttonColors(
            containerColor = currentColor
        )

    ) {
        Text(text = position.toString(), textAlign = TextAlign.Center)
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
