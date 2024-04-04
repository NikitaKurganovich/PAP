package dev.babananick.pap.modules

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class LectureModule(
    val academic_module: String? = null,
    val lectures: List<LectureInModule>? = null,
)