package dev.babananick.pap.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
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
    val listState = rememberLazyListState()
    var buttonWidth by remember { mutableStateOf(0.dp) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    LaunchedEffect(currentQuestion) {
        val offsetDp = screenWidth.div(2) - buttonWidth.div(2) +
                18.dp + 14.dp + 3.dp
        val offset = offsetDp.value.toInt()
        listState.animateScrollToItem(
            index = currentQuestion,
            scrollOffset = -offset
        )
    }
    LazyRow(
        state = listState,
        modifier = modifier,
    ) {
        item {
            Spacer(Modifier.width(9.dp))
        }
        itemsIndexed(test.questions!!) { index, question ->
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
                },
                isAnswered = question.isAnswered,
                isNeedToAnswer = question.isSkipped
            )
        }
        item {
            Spacer(Modifier.width(9.dp))
        }
    }
}