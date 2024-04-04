package dev.babananick.pap.questions

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
open class Question(
    open val question: String? = null
)

