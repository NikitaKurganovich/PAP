package dev.babananick.pap.questions

import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.AnswerVariantWithScale

@IgnoreExtraProperties
data class QuestionWithVariants(
    override val question: String? = null,
    val answer_variants: List<AnswerVariantWithScale>? = null
): Question()
