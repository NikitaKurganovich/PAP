package dev.babananick.pap.core.model.interpretation

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties

@Immutable
@IgnoreExtraProperties
data class InterpretationWithStringResult(
    val lead_scale: String? = null,
    val result: String? = null
): dev.babananick.pap.core.model.interpretation.Interpretation()
