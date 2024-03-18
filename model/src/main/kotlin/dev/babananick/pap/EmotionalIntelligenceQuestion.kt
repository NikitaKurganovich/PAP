package dev.babananick.pap

import java.util.*

data class EmotionalIntelligenceQuestion(
    val question: String = "",
    val related_scale: String = "",
    val answer_variants: HashMap<String, Int> = hashMapOf()
)