package dev.babananick.pap.core.model.tests

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.core.model.AnswerVariantWithScale
import dev.babananick.pap.core.model.interpretation.InterpretationMBTI
import dev.babananick.pap.core.model.questions.Question
import dev.babananick.pap.core.model.questions.QuestionWithScale

@Immutable
@IgnoreExtraProperties
data class TestMBTI(
    override val questions: List<MBTIQuestion>? = null,
    override val name: String? = null,
    override val interpretation: List<InterpretationMBTI>? = null,
): Test()

@Immutable
@IgnoreExtraProperties
data class MBTIQuestion(
    override val question: String? = null,
    val answer_variants: List<AnswerVariantWithScale>? = null
): Question()