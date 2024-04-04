package dev.babananick.pap

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class AnswerVariantWithScale(
    val answer: String? = null,
    val related_scale: String? = null
)
