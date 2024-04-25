package dev.babananick.pap.core.model.questions

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties

@Immutable
@IgnoreExtraProperties
open class Question(
    open val question: String? = null
){
    var isAnswered = false
    open val isNeedToAnswer = true
    var currentSelected: String? = null
    var isSkipped = false
}

