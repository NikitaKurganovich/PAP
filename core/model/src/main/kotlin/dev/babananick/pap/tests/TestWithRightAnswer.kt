package dev.babananick.pap.tests

import dev.babananick.pap.Interpretation
import dev.babananick.pap.questions.Question
import dev.babananick.pap.questions.QuestionWithRightAnswer
import dev.babananick.pap.questions.QuestionWithVariants


data class TestWithRightAnswer(
    override val name: String,
    override val questions: List<QuestionWithRightAnswer>,
    override val interpretation: List<Interpretation>,
    ): Test