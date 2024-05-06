package dev.babananick.pap.core.model.interpretation

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties

@Immutable
@IgnoreExtraProperties
data class InterpretationMBTI(
    val description: String = "",
    val type: String = ""
): Interpretation()
