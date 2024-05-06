package dev.babananick.pap.feature.tests.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import dev.babananick.pap.core.model.questions.Question
import dev.babananick.pap.core.model.questions.QuestionWithScale
import dev.babananick.pap.core.model.questions.QuestionWithVariants
import dev.babananick.pap.core.model.tests.MBTIQuestion
import dev.babananick.pap.core.model.tests.Test
import dev.babananick.pap.core.model.tests.TestWithSharedVariants
import dev.babananick.pap.ui.components.radiobuttongroup.RadioButtonGroup
import dev.babananick.pap.ui.theme.R as theme
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

            is MBTIQuestion ->{
                MBTIContent()
            }
        }
    }

    @Composable
    fun QuestionWithVariantsContent() {
        question as QuestionWithVariants
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var currentlySelected: String? by remember(key) {
                mutableStateOf(question.currentSelected)
            }
            Text(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(theme.dimen.screen_content_horizontal_padding)),
                text = question.question!!,
                style = MaterialTheme.typography.bodyLarge
            )
            RadioButtonGroup(
                question = question,
                currentlySelected = remember { { currentlySelected } },
                onVariantChange = remember {
                    { localAnswer ->
                        question.scale = question.answer_variants!!.find {
                            it.answer == localAnswer
                        }!!.related_scale
                        question.isAnswered = true
                        currentlySelected = localAnswer
                        question.currentSelected = localAnswer
                    }
                }
            )
        }
    }

    @Composable
    fun QuestionWithScaleContent() {
        test as TestWithSharedVariants
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var currentlySelected: String? by remember(key) {
                mutableStateOf(question.currentSelected)
            }
            Text(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(theme.dimen.screen_content_horizontal_padding)),
                text = question.question!!,
                style = MaterialTheme.typography.bodyLarge
            )
            RadioButtonGroup(
                test = test,
                currentlySelected = remember { { currentlySelected } },
                onVariantChange = remember {
                    {
                        question.isAnswered = true
                        currentlySelected = it
                        question.currentSelected = it
                    }
                }
            )
        }
    }

    @Composable
    fun MBTIContent(){

        question as MBTIQuestion
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var currentlySelected: String? by remember(key) {
                mutableStateOf(question.currentSelected)
            }
            Text(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(theme.dimen.screen_content_horizontal_padding)),
                text = question.question!!,
                style = MaterialTheme.typography.bodyLarge
            )
            RadioButtonGroup(
                question = question,
                currentlySelected = remember { { currentlySelected } },
                onVariantChange = remember {
                    {
                        question.isAnswered = true
                        currentlySelected = it
                        question.currentSelected = it
                    }
                }
            )
        }
    }
}
