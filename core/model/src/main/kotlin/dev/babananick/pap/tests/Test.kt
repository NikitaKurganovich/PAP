package dev.babananick.pap.tests

import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.interpretation.Interpretation
import dev.babananick.pap.questions.Question


@IgnoreExtraProperties
open class Test(
    open val name: String? = null,
    open val questions: List<Question>? = null,
    open val interpretation: List<Interpretation>? = null,
)