package dev.babananick.pap.questions

import dev.babananick.pap.AnswerVariant

data class QuestionWithVariants(
    override val questionText: String,
    val answerVariants: List<AnswerVariant>
): Question
