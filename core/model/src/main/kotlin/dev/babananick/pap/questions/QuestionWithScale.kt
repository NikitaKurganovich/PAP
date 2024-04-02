package dev.babananick.pap.questions

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class QuestionWithScale(
    override val question: String,
    val related_scale: String
): Question()