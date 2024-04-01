package dev.babananick.pap

data class InterpretationWithRanges(
    override val lead_scale: String,
    val results: List<ResultWithRange>
): Interpretation
