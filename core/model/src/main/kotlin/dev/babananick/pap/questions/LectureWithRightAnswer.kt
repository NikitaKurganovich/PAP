package dev.babananick.pap.questions


data class LectureWithRightAnswer(
    override val questionText: String,
    val correctAnswer: String,
    val availableAnswers: List<String>,
): Question