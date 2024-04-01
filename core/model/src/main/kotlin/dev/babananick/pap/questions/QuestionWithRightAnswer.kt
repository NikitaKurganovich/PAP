package dev.babananick.pap.questions

data class QuestionWithRightAnswer(
    override val question: String,
    val correct_answer: String,
    val available_answers: List<String>,
): Question
