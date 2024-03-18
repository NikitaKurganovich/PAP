package dev.babananick.pap


data class LectureQuestion(
    val question: String = "",
    val correct_answer: String = "",
    val available_answers: List<String> = listOf()
)