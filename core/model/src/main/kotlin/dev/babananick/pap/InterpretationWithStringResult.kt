package dev.babananick.pap

data class InterpretationWithStringResult(
    override val lead_scale: String,
    val result: String
): Interpretation
