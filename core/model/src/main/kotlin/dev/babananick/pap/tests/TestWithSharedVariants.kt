package dev.babananick.pap.tests

import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.AnswerVariantWithScore
import dev.babananick.pap.interpretation.InterpretationWithRanges
import dev.babananick.pap.questions.QuestionWithScale

@IgnoreExtraProperties
data class TestWithSharedVariants(
    override val questions: List<QuestionWithScale>? = null,
    val answer_variants: List<AnswerVariantWithScore>? = null,
    override val name: String? = null,
    override val interpretation: List<InterpretationWithRanges>? = null,
): Test()
