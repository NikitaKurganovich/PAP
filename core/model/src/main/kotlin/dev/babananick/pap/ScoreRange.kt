package dev.babananick.pap

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties

@Immutable
@IgnoreExtraProperties
data class ScoreRange(
    val from: Long? = null,
    val to: Long? = null
){
    fun isInRange(score: Long): Boolean {
        return score >= from!! && score <= to!!
    }
}
