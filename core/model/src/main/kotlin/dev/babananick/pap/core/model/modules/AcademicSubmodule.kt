package dev.babananick.pap.core.model.modules

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties

@Immutable
@IgnoreExtraProperties
@Suppress("PropertyName")
data class AcademicSubmodule(
    val name: String? = null,
    val question_quantity: Long? = null
)
