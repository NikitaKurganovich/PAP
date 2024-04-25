package dev.babananick.pap.ui.components.results

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ResultsPrimary(
    modifier: Modifier = Modifier,
    content: @Composable ()-> Unit
) {
    Box(
        modifier = modifier
            .size(
                width = 340.dp,
                height = 394.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(
                    height = 350.dp,
                    width = 280.dp
                )
                .rotate(-5f)
                .offset(
                    x = (-6).dp,
                    y = (16).dp
                )
                .background(Color(0xFF31674D), resultsShape)
                .clip(resultsShape)

        )
        Box(
            modifier = Modifier
                .size(
                    height = 350.dp,
                    width = 280.dp
                )
                .rotate(10f)
                .offset(
                    x = (-6).dp,
                    y = (6).dp
                )
                .background(Color(0xFFBBDACB), resultsShape)
                .clip(resultsShape)
        )
        Box(
            modifier = Modifier
                .size(
                    height = 350.dp,
                    width = 280.dp
                )
                .background(Color(0xFFEEFDEF), resultsShape)
                .clip(resultsShape),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

val resultsShape: RoundedCornerShape
    @Composable
    get() {
        val shape = RoundedCornerShape(50.dp)
        return remember { shape }
    }