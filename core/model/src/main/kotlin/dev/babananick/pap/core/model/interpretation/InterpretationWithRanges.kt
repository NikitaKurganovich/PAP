package dev.babananick.pap.core.model.interpretation

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.core.model.tests.ResultWithRange

@Immutable
@IgnoreExtraProperties
data class InterpretationWithRanges(
    val lead_scale: String? = null,
    val results: List<dev.babananick.pap.core.model.tests.ResultWithRange>? = null
): dev.babananick.pap.core.model.interpretation.Interpretation()
