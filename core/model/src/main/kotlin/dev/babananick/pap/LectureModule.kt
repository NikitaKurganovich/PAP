package dev.babananick.pap

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class LectureModule(
    val module_name: String = "",
    val submodulesNames: List<String> = listOf(),
)