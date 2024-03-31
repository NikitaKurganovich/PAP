package dev.babananick.pap.tests

import dev.babananick.pap.AnswerVariant
import dev.babananick.pap.questions.QuestionWithScale

data class PersonalTestWithSharedVariants(
    override val questions: List<QuestionWithScale>,
    val answerVariants: List<AnswerVariant>,
): PersonalTest
