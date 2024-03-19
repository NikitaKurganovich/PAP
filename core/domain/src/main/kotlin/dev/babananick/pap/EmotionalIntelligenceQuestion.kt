package dev.babananick.pap



data class EmotionalIntelligenceQuestion(
    val question: String = "",
    val relatedScale: String = "",
    val answerVariants: HashMap<String, Int> = hashMapOf()
)