package dev.babananick.pap.ui.components.radiobuttongroup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.babananick.pap.core.model.questions.QuestionWithVariants
import dev.babananick.pap.core.model.tests.TestWithSharedVariants


@Composable
fun RadioButtonGroup(
    modifier: Modifier = Modifier,
    question: dev.babananick.pap.core.model.questions.QuestionWithVariants,
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
    test: dev.babananick.pap.core.model.tests.TestWithSharedVariants,
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