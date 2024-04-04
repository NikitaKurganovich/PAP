package dev.babananick.pap

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class AnswerVariantWithScore(
    val answer: String? = null,
    val related_score: Long? = null
)
