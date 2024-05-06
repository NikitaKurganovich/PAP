package dev.babananick.pap.core.model.tests

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.core.model.interpretation.Interpretation
import dev.babananick.pap.core.model.questions.Question

@Immutable
@IgnoreExtraProperties
open class Test(
    open val name: String? = null,
    open val questions: List<Question>? = null,
    open val interpretation: List<Interpretation>? = null,
    open val description: String? = null
)