package dev.babananick.pap.modules

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class TestModule(
    val test_module: String? = null,
   // val module_icon:
    val tests: List<TestInModule>? = null
)
