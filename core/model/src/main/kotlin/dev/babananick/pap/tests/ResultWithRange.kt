package dev.babananick.pap.tests

import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.ScoreRange

@IgnoreExtraProperties
data class ResultWithRange(
    val range: ScoreRange? = null,
    val result: String? = null
)
