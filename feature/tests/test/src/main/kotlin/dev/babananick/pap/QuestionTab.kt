package dev.babananick.pap

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import dev.babananick.pap.questions.QuestionWithScale
import dev.babananick.pap.questions.QuestionWithVariants
import dev.babananick.pap.tests.Test
import dev.babananick.pap.tests.TestWithSharedVariants
import kotlinx.coroutines.flow.MutableStateFlow

data class QuestionTab(
    val test: Test,
    val questionPosition: Int,
) : Tab {

    override val options: TabOptions
        @Composable
        get() {
            return remember {
                TabOptions(
                    index = questionPosition.toUShort(),
                    title = "${questionPosition + 1}",
                )
            }
        }

    @Composable
    override fun Content() {
        when(val question = test.questions!![questionPosition]){
            is QuestionWithVariants ->{
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
            is QuestionWithScale ->{
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
fun InnerTabNavigation(
    test: Test,
    currentPos: MutableStateFlow<Int>
) {
    Row(
        modifier = Modifier.padding(16.dp).horizontalScroll(rememberScrollState())
    ) {
        test.questions!!.forEachIndexed { index, _ ->
            TabButton(
                QuestionTab(test, index)
            ) { tabPositioning(index, currentPos) }
        }
    }
}

fun tabPositioning(pos: Int, currentPos: MutableStateFlow<Int>) {
    currentPos.value = pos
}

@Composable
private fun RowScope.TabButton(
    tab: Tab,
    onClick: (() -> Unit)
) {
    val tabNavigator = LocalTabNavigator.current
    Button(
        onClick = {
            onClick()
            tabNavigator.current = tab
        },
        modifier = Modifier.padding(3.dp)
    ) {
        Text(text = tab.options.title)
    }
}

@Composable
fun NextAndPrevious(
    test: Test,
    isNextEnabled: Boolean,
    isPreviousEnabled: Boolean,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    currentQuestion: Int
) {
    Row {
        TabPrevious(test, isPreviousEnabled, onPrevious, currentQuestion)
        TabNext(test, isNextEnabled, onNext, currentQuestion)
    }
}

@Composable
fun TabNext(
    test: Test,
    isEnabled: Boolean,
    onNext: () -> Unit,
    currentQuestion: Int
) {
    val navigator = LocalTabNavigator.current
    Button(
        enabled = isEnabled,
        onClick = {
            onNext()
            navigator.current = QuestionTab(test, currentQuestion)
        },
        modifier = Modifier.padding(3.dp)
    ) {
        Text("Next")
    }
}
@Composable
fun TabPrevious(
    test: Test,
    isEnabled: Boolean,
    onPrevious: () -> Unit,
    currentQuestion: Int
) {
    val navigator = LocalTabNavigator.current
    Button(
        enabled = isEnabled,
        onClick = {
            onPrevious()
            navigator.current = QuestionTab(test, currentQuestion)
        },
        modifier = Modifier.padding(3.dp)
    ) {
        Text("Previous")
    }
}