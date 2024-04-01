package dev.babananick.pap.tests

import dev.babananick.pap.Interpretation
import dev.babananick.pap.questions.Question

interface Test{
    val name: String
    val questions: List<Question>
    val interpretation: List<Interpretation>
}
