package dev.babananick.pap

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import dev.babananick.pap.questions.Question
import dev.babananick.pap.questions.QuestionWithScale
import dev.babananick.pap.questions.QuestionWithVariants
import dev.babananick.pap.radiobuttongroup.RadioButtonGroup
import dev.babananick.pap.tests.Test
import dev.babananick.pap.tests.TestWithSharedVariants
import dev.babananick.pap.text.QuestionText

data class QuestionScreen(
    val test: Test,
    val question: Question,
) : Screen {
    override val key: ScreenKey
        get() = question.toString()

    @Composable
    override fun Content() {
        when (question) {
            is QuestionWithVariants -> {
                QuestionWithVariantsContent()
            }

            is QuestionWithScale -> {
                QuestionWithScaleContent()
            }
        }
    }

    @Composable
    fun QuestionWithVariantsContent() {
        question as QuestionWithVariants
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var currentlySelected: String? by remember(key) {
                mutableStateOf(question.currentSelected)
            }
            QuestionText(
                modifier = Modifier.padding(horizontal = 20.dp),
                question = question.question!!
            )
            RadioButtonGroup(
                question = question,
                currentlySelected = remember { { currentlySelected } },
                onVariantChange = remember {
                    {
                        currentlySelected = it
                        question.currentSelected = it
                    }
                }
            )
        }
    }

    @Composable
    fun QuestionWithScaleContent() {
        test as TestWithSharedVariants
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var currentlySelected: String? by remember(key) {
                mutableStateOf(question.currentSelected)
            }
            QuestionText(
                modifier = Modifier.padding(horizontal = 20.dp),
                question = question.question!!
            )
            RadioButtonGroup(
                test = test,
                currentlySelected = remember { { currentlySelected } },
                onVariantChange = remember {
                    {
                        currentlySelected = it
                        question.currentSelected = it
                    }
                }
            )
        }
    }
}
