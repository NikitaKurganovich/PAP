package dev.babananick.pap.questions

import dev.babananick.pap.AnswerVariantWithScale

data class QuestionWithVariants(
    override val question: String,
    val answer_variants: List<AnswerVariantWithScale>
): Question
