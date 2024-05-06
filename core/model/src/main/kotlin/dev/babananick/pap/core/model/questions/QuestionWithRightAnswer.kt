package dev.babananick.pap.core.model.questions

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties

@Immutable
@IgnoreExtraProperties
data class QuestionWithRightAnswer(
    override val question: String? = null,
    val correct_answer: String? = null,
    val available_answers: List<String>? = null,
): Question() {
    var isAnsweredCorrectly: Boolean = false

}
