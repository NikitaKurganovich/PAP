package dev.babananick.pap.tests

import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.interpretation.Interpretation
import dev.babananick.pap.questions.QuestionWithRightAnswer

@IgnoreExtraProperties
data class TestWithRightAnswer(
    override val name: String? = null,
    override val questions: List<QuestionWithRightAnswer>? = null,
    override val interpretation: List<Interpretation>? = null,
    ): Test()