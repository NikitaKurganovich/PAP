package dev.babananick.pap

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import dev.babananick.pap.questions.QuestionWithScale
import dev.babananick.pap.questions.QuestionWithVariants
import dev.babananick.pap.tests.Test
import dev.babananick.pap.tests.TestWithSharedVariants
import dev.babananick.pap.ui.theme.Green40
import dev.babananick.pap.ui.theme.Purple80

data class QuestionScreen(
    val test: Test,
    val questionPosition: Int,
) : Screen {

    @Composable
    override fun Content() {
        when (val question = test.questions!![questionPosition]) {
            is QuestionWithVariants -> {
                Column {
                    Text(question.question!!)
                    question.answer_variants!!.forEach { questionText ->
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = questionText.answer!!
                        )
                    }
                }

            }

            is QuestionWithScale -> {
                test as TestWithSharedVariants
                Column {
                    Text(question.question!!)
                    test.answer_variants!!.forEach { questionText ->
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = questionText.answer!!
                        )
                    }
                }
            }
        }
    }
}

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
                    navigator.push(QuestionScreen(test, index))
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


@Composable
private fun NavigatorButton(
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

