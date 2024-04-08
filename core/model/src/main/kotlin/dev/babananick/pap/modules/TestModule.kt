package dev.babananick.pap.modules

import android.content.res.Resources
import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.TestResources

@Immutable
@IgnoreExtraProperties
data class TestModule(
    val test_module: String? = null,
    val resources: TestResources? = null,
    val tests: List<TestInModule>? = null
)
