package dev.babananick.pap.core.model.tests

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.core.model.interpretation.InterpretationMBTI
import dev.babananick.pap.core.model.questions.QuestionWithScale

@Immutable
@IgnoreExtraProperties
data class TestMBTI(
    override val questions: List<QuestionWithScale>? = null,
    override val name: String? = null,
    override val interpretation: List<InterpretationMBTI>? = null,
): Test()
