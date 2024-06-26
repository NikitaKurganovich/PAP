package dev.babananick.pap

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties

@Immutable
@IgnoreExtraProperties
data class ProfileData(
    val email: String? = null,
    val lecture_results: List<LectureResult>? = null
)
