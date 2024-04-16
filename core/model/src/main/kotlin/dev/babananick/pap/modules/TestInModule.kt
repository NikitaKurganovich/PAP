package dev.babananick.pap.modules

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties

@Immutable
@IgnoreExtraProperties
data class TestInModule(
    val name: String? = null,
    val id: String? = null,
    val question_quantity: Long? = null
)
