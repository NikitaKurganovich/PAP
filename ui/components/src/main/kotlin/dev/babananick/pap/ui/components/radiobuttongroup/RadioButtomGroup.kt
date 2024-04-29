package dev.babananick.pap.ui.components.radiobuttongroup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import dev.babananick.pap.core.model.questions.QuestionWithVariants
import dev.babananick.pap.core.model.tests.TestWithSharedVariants
import dev.babananick.pap.ui.components.R

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
                modifier = Modifier.padding(dimensionResource(R.dimen.answer_radio_button_padding)),
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
                modifier = Modifier.padding(dimensionResource(R.dimen.answer_radio_button_padding)),
                onVariantChange = onVariantChange,
                currentlySelected = currentlySelected,
                variantText = answer.answer!!
            )
        }
    }
}
