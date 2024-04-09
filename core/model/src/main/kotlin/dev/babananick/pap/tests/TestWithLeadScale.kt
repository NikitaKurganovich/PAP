package dev.babananick.pap.tests

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.interpretation.InterpretationWithStringResult
import dev.babananick.pap.questions.QuestionWithVariants

@Immutable
@IgnoreExtraProperties
data class TestWithLeadScale(
    override val questions: List<QuestionWithVariants>? = null,
    override val name: String? = null,
    override val interpretation: List<InterpretationWithStringResult>? = null,
): Test()