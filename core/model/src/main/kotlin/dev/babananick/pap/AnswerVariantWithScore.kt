package dev.babananick.pap

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties

@Immutable
@IgnoreExtraProperties
data class AnswerVariantWithScore(
    val answer: String? = null,
    val related_score: Long? = null
)
