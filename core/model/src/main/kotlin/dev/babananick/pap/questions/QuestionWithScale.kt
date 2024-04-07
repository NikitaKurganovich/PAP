package dev.babananick.pap.questions

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties

@Immutable
@IgnoreExtraProperties
data class QuestionWithScale(
    override val question: String? = null,
    val related_scale: String? = null
): Question()