package dev.babananick.pap.tests

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties

@Immutable
@IgnoreExtraProperties
data class TestResult(
    val score: Long? = null,
    val max_score: Long? = null
)
