package dev.babananick.pap.tests

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class TestResult(
    val score: Long? = null,
    val max_score: Long? = null
)
