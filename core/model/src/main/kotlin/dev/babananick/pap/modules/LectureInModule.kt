package dev.babananick.pap.modules

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties

@Immutable
@IgnoreExtraProperties
data class LectureInModule(
    val name: String? = null,
    val question_quantity: Long? = null
)
