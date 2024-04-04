package dev.babananick.pap.interpretation

import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.tests.ResultWithRange

@IgnoreExtraProperties
data class InterpretationWithRanges(
    val lead_scale: String? = null,
    val results: List<ResultWithRange>? = null
): Interpretation
