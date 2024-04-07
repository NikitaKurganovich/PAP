package dev.babananick.pap

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import dev.babananick.pap.questions.Question
import dev.babananick.pap.questions.QuestionWithScale
import dev.babananick.pap.questions.QuestionWithVariants
import dev.babananick.pap.radiobuttongroup.RadioButtonGroup
import dev.babananick.pap.tests.Test
import dev.babananick.pap.tests.TestWithSharedVariants

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
        Column {
            var currentlySelected: String? by remember(key) {
                mutableStateOf(question.currentSelected)
            }
            Text(question.question!!)
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
        Column {
            var currentlySelected: String? by remember(key) {
                mutableStateOf(question.currentSelected)
            }
            Text(question.question!!)
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
