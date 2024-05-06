package dev.babananick.pap.core.model.tests

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.core.model.ScoreRange

@Immutable
@IgnoreExtraProperties
data class ResultWithRange(
    val range: ScoreRange? = null,
    val result: String? = null
)
