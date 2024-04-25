package dev.babananick.pap.core.model.tests

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.core.model.AnswerVariantWithScore
import dev.babananick.pap.core.model.interpretation.InterpretationWithRanges
import dev.babananick.pap.core.model.questions.QuestionWithScale

@Immutable
@IgnoreExtraProperties
data class TestWithSharedVariants(
    override val questions: List<dev.babananick.pap.core.model.questions.QuestionWithScale>? = null,
    val answer_variants: List<dev.babananick.pap.core.model.AnswerVariantWithScore>? = null,
    override val name: String? = null,
    override val interpretation: List<dev.babananick.pap.core.model.interpretation.InterpretationWithRanges>? = null,
): dev.babananick.pap.core.model.tests.Test()
