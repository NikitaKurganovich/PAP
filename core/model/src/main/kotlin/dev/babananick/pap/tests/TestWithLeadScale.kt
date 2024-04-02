package dev.babananick.pap.tests

import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.Interpretation
import dev.babananick.pap.InterpretationWithRanges
import dev.babananick.pap.questions.QuestionWithVariants
@IgnoreExtraProperties
data class TestWithLeadScale(
    override val questions: List<QuestionWithVariants>,
    override val name: String,
    override val interpretation: List<InterpretationWithRanges>
): Test()