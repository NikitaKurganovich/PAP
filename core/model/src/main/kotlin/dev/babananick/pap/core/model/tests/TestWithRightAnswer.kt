package dev.babananick.pap.core.model.tests

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.core.model.interpretation.Interpretation
import dev.babananick.pap.core.model.questions.QuestionWithRightAnswer

@Immutable
@IgnoreExtraProperties
data class TestWithRightAnswer(
    override val name: String? = null,
    override val questions: List<dev.babananick.pap.core.model.questions.QuestionWithRightAnswer>? = null,
    override val interpretation: List<dev.babananick.pap.core.model.interpretation.Interpretation>? = null,
    ): dev.babananick.pap.core.model.tests.Test()