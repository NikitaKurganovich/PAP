package dev.babananick.pap.interpretation

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class InterpretationWithStringResult(
    val lead_scale: String? = null,
    val result: String? = null
): Interpretation
