package dev.babananick.pap.tests

import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.AnswerVariantWithScore
import dev.babananick.pap.InterpretationWithRanges
import dev.babananick.pap.questions.QuestionWithScale

@IgnoreExtraProperties
data class TestWithSharedVariants(
    override val questions: List<QuestionWithScale>,
    val answer_variants: List<AnswerVariantWithScore>,
    override val name: String,
    override val interpretation: List<InterpretationWithRanges>,
): Test()
