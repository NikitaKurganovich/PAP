package dev.babananick.pap

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ScoreRange(
    val from: Long? = null,
    val to: Long? = null
)
