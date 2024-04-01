package dev.babananick.pap.tests

import dev.babananick.pap.Interpretation
import dev.babananick.pap.questions.QuestionWithVariants

data class TestWithLeadScale(
    override val questions: List<QuestionWithVariants>,
    override val name: String,
    override val interpretation: List<Interpretation>
): Test