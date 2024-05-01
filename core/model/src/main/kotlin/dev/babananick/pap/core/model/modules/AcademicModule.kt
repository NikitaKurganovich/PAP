package dev.babananick.pap.core.model.modules

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.core.model.ModuleResources

@Immutable
@IgnoreExtraProperties
@Suppress("PropertyName")
data class AcademicModule(
    val academic_module: String? = null,
    val lectures: List<AcademicSubmodule>? = null,
    val resources: ModuleResources? = null,
)