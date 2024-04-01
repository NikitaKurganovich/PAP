package dev.babananick.pap.questions

data class QuestionWithScale(
    override val question: String,
    val related_scale: String
): Question