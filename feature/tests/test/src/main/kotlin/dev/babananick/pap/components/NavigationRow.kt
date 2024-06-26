package dev.babananick.pap.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
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
    modifier: Modifier = Modifier,
    currentQuestion: Int,
    test: Test,
    navigator: Navigator,
    fetcher: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()
    var buttonWidth by remember { mutableIntStateOf(0) }
    Row(
        modifier = modifier
            .horizontalScroll(scrollState)
    ) {
        Spacer(Modifier.width(9.dp))
        test.questions!!.forEachIndexed { index, question ->
            NavigatorButton(
                modifier = Modifier,
                currentQuestion = currentQuestion,
                position = index + 1,
                onClick = {
                    fetcher(index)
                    navigator.push(QuestionScreen(test, question))
                },
                onSizeMeasured = { width ->
                    buttonWidth = width
                }
            )
        }
        Spacer(Modifier.width(9.dp))
    }
    LaunchedEffect(currentQuestion) {
        scrollState.animateScrollTo(buttonWidth * currentQuestion)
    }
}