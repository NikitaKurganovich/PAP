package dev.babananick.pap.core.model.modules

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties

@Immutable
@IgnoreExtraProperties
data class LectureModule(
    val academic_module: String? = null,
    val lectures: List<dev.babananick.pap.core.model.modules.LectureInModule>? = null,
)