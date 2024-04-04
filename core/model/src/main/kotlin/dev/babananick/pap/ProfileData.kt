package dev.babananick.pap

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ProfileData(
    val email: String? = null,
    val lecture_results: List<LectureResult>? = null
)
