package dev.babananick.pap.questions

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties

@Immutable
@IgnoreExtraProperties
open class Question(
    open val question: String? = null
){
    var isAnswered = false
    var currentSelected: String? = null
}

