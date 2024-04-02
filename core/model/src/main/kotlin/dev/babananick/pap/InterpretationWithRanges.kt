package dev.babananick.pap

data class InterpretationWithRanges(
    val lead_scale: String,
    val results: List<ResultWithRange>
): Interpretation
