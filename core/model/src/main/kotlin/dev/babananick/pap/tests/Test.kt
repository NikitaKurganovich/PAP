package dev.babananick.pap.tests

import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.Interpretation
import dev.babananick.pap.questions.Question


@IgnoreExtraProperties
open class Test(
   open val name: String = "",
   open val questions: List<Question> = listOf(),
   open val interpretation: List<Interpretation> = listOf(),
)