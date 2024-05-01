package dev.babananick.pap.core.model.modules

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.core.model.ModuleResources

@Immutable
@IgnoreExtraProperties
data class TestModule(
    val test_module: String? = null,
    val resources: ModuleResources? = null,
    val tests: List<TestInModule>? = null
)
