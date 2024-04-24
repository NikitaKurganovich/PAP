package dev.babananick.pap.buttons

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.babananick.pap.theme.PAPTypo

@Composable
fun NavigatorButton(
    modifier: Modifier = Modifier,
    currentQuestion: Int,
    position: Int,
    onClick: (() -> Unit),
    isAnswered: Boolean,
    isNeedToAnswer: Boolean = false,
    onSizeMeasured: (Dp) -> Unit,
) {
    val currentBackgroundColor by rememberBackgroundColor(currentQuestion, position)
    val currentBorderColor by rememberBorderColor(currentQuestion, isAnswered,position,isNeedToAnswer)
    val currentTextColor by rememberTextColor(currentQuestion,isAnswered,position,isNeedToAnswer)
    val shape by remember { mutableStateOf(RoundedCornerShape(100)) }

    Box(
        modifier = modifier
            .padding(7.dp)
            .background(currentBackgroundColor, shape)
            .border(4.dp,currentBorderColor, shape)
            .size(48.dp)
            .clip(shape)
            .clickable(onClick = onClick)
            .onGloballyPositioned { coordinates ->
                onSizeMeasured(coordinates.size.width.dp)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = position.toString(),
            textAlign = TextAlign.Center,
            style = PAPTypo.navigateTextStyle,
            color = currentTextColor
        )
    }
}


@Composable
private fun rememberTextColor(
    currentQuestion: Int,
    isAnswered: Boolean,
    position: Int,
    isNeedToAnswer: Boolean,
): State<Color> {
    val color by remember(currentQuestion, isAnswered, isNeedToAnswer) {
        derivedStateOf {
            when {
                position == currentQuestion + 1 -> Color.White
                isAnswered -> Color(0xFF31674D)
                isNeedToAnswer -> Color(0xFFBC1C1C)
                else -> Color(0xFFBBDACB)
            }
        }
    }
    return animateColorAsState(color)
}

@Composable
private fun rememberBackgroundColor(
    currentQuestion: Int,
    position: Int,
): State<Color> {
    val color by remember(currentQuestion) {
        derivedStateOf {
            if (position == currentQuestion + 1) {
                Color(0xFF31674D)
            } else {
                Color.White
            }
        }
    }
    return animateColorAsState(color)
}

@Composable
private fun rememberBorderColor(
    currentQuestion: Int,
    isAnswered: Boolean,
    position: Int,
    isNeedToAnswer: Boolean,
): State<Color> {
    val color by remember(currentQuestion, isAnswered, isNeedToAnswer) {
        derivedStateOf {
            when {
                position == currentQuestion + 1 -> Color(0xFF31674D)
                isAnswered -> Color(0xFF31674D)
                isNeedToAnswer -> Color(0xFFBC1C1C)
                else -> Color(0xFFBBDACB)
            }
        }
    }
    return animateColorAsState(color)
}


