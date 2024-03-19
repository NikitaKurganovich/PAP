package dev.babananick.pap


data class LectureQuestion(
    val question: String = "",
    val correctAnswer: String = "",
    val availableAnswers: List<String> = listOf()
)