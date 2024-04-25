package dev.babananick.pap.core.model

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties

@Immutable
@IgnoreExtraProperties
data class AnswerVariantWithScale(
    val answer: String? = null,
    val related_scale: String? = null
)
