package dev.babananick.pap.modules

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class LectureModule(
    val module_name: String? = null,
    val submodules_names: List<String>? = null,
)