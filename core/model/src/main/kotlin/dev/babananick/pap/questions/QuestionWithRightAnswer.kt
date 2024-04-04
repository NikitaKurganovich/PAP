package dev.babananick.pap.questions

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class QuestionWithRightAnswer(
    override val question: String? = null,
    val correct_answer: String? = null,
    val available_answers: List<String>? = null,
): Question()
