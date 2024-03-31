package dev.babananick.pap.questions

data class QuestionWithScale(
    override val questionText: String,
    val relatedScale: String
): Question