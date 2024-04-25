package dev.babananick.pap.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
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
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val density = LocalDensity.current
    val listState = rememberLazyListState()

    val width = displayMetrics.widthPixels
    val adjustmentPx = with(density){
        (24.dp + 7.dp + 9.dp).roundToPx()
    }

    val offsetPx = width/2 - adjustmentPx

    LazyRow(
        state = listState,
        contentPadding = PaddingValues(horizontal = 9.dp),
        modifier = modifier,
    ) {
        itemsIndexed(test.questions!!) { index, question ->
            NavigatorButton(
                modifier = Modifier,
                currentQuestion = currentQuestion,
                position = index + 1,
                onClick = {
                    fetcher(index)
                    navigator.push(QuestionScreen(test, question))
                },
                isAnswered = question.isAnswered,
                isNeedToAnswer = question.isSkipped
            )
        }
    }

    LaunchedEffect(currentQuestion) {
        listState.animateScrollToItem(
            index = currentQuestion,
            scrollOffset = -offsetPx
        )
    }
}