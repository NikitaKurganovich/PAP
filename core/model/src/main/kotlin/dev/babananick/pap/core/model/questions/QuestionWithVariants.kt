package dev.babananick.pap.core.model.questions

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.core.model.AnswerVariantWithScale

@Immutable
@IgnoreExtraProperties
data class QuestionWithVariants(
    val answer_variants: List<AnswerVariantWithScale>? = null,
    override val question: String? = null,
) : Question() {
    var scale: String? = null
}
