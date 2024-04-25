package dev.babananick.pap.core.model.tests

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.core.model.interpretation.InterpretationWithStringResult
import dev.babananick.pap.core.model.questions.QuestionWithVariants

@Immutable
@IgnoreExtraProperties
data class TestWithLeadScale(
    override val questions: List<dev.babananick.pap.core.model.questions.QuestionWithVariants>? = null,
    override val name: String? = null,
    override val interpretation: List<dev.babananick.pap.core.model.interpretation.InterpretationWithStringResult>? = null,
): dev.babananick.pap.core.model.tests.Test()