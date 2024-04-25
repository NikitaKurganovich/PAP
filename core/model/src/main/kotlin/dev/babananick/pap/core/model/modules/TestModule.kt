package dev.babananick.pap.core.model.modules

import android.content.res.Resources
import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.core.model.TestResources

@Immutable
@IgnoreExtraProperties
data class TestModule(
    val test_module: String? = null,
    val resources: dev.babananick.pap.core.model.TestResources? = null,
    val tests: List<dev.babananick.pap.core.model.modules.TestInModule>? = null
)
