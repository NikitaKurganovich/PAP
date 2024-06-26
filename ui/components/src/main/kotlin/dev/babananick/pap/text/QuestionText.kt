package dev.babananick.pap.text

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QuestionText(
    modifier: Modifier = Modifier,
    question: String,
) {
    Text(
        modifier = modifier
            .height(100.dp)
            .verticalScroll(rememberScrollState()),
        text = question,
        style = MaterialTheme.typography.bodyLarge
    )
}