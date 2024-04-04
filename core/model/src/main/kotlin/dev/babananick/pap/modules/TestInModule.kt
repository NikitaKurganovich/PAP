package dev.babananick.pap.modules

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class TestInModule(
    val name: String? = null,
    val question_quantity: Long? = null
)
