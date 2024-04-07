package dev.babananick.pap.tests

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.ScoreRange

@Immutable
@IgnoreExtraProperties
data class ResultWithRange(
    val range: ScoreRange? = null,
    val result: String? = null
)
