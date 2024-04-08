package dev.babananick.pap.radiobuttongroup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.babananick.pap.questions.QuestionWithVariants
import dev.babananick.pap.tests.TestWithSharedVariants


@Composable
fun RadioButtonGroup(
    modifier: Modifier = Modifier,
    question: QuestionWithVariants,
    currentlySelected: () -> String?,
    onVariantChange: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) { 
        question.answer_variants!!.forEach { answer ->
            AnswerRadioButton(
                modifier = Modifier.padding((10.5).dp),
                onVariantChange = onVariantChange,
                currentlySelected = currentlySelected,
                variantText = answer.answer!!
            )
        }
    }
}

@Composable
fun RadioButtonGroup(
    modifier: Modifier = Modifier,
    test: TestWithSharedVariants,
    currentlySelected: () -> String?,
    onVariantChange: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        test.answer_variants!!.forEach { answer ->
            AnswerRadioButton(
                modifier = Modifier.padding((10.5).dp),
                onVariantChange = onVariantChange,
                currentlySelected = currentlySelected,
                variantText = answer.answer!!
            )
        }
    }
}