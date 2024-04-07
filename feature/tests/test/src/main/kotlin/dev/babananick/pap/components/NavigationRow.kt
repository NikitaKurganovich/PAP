package dev.babananick.pap.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import dev.babananick.pap.QuestionScreen
import dev.babananick.pap.buttons.NavigatorButton
import dev.babananick.pap.tests.Test

@Composable
fun InnerNavigation(
    currentQuestion: Int,
    test: Test,
    navigator: Navigator,
    fetcher: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()
    var buttonWidth by remember { mutableIntStateOf(0) }
    Row(
        modifier = Modifier.padding(16.dp).horizontalScroll(scrollState)
    ) {
        test.questions!!.forEachIndexed { index, _ ->
            NavigatorButton(
                currentQuestion = currentQuestion,
                position = index + 1,
                onClick = {
                    fetcher(index)
                    navigator.push(QuestionScreen(test, test.questions!![index]))
                },
                onSizeMeasured = { width ->
                    buttonWidth = width
                }
            )
        }
    }
    LaunchedEffect(currentQuestion) {
        scrollState.animateScrollTo(buttonWidth * currentQuestion)
    }
}