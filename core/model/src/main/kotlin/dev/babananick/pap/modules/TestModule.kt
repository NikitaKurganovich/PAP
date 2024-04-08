package dev.babananick.pap.modules

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties

@Immutable
@IgnoreExtraProperties
data class TestModule(
    val test_module: String? = null,
   // val module_icon:
    val tests: List<TestInModule>? = null
)
