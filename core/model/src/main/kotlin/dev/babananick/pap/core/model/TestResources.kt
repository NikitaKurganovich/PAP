package dev.babananick.pap.core.model

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
@Immutable
data class TestResources(
    val icon: String? = null
)
