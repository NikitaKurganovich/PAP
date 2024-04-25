package dev.babananick.pap.core.model

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties

@Immutable
@IgnoreExtraProperties
data class ProfileData(
    val email: String? = null,
    val lecture_results: List<dev.babananick.pap.core.model.LectureResult>? = null
)
