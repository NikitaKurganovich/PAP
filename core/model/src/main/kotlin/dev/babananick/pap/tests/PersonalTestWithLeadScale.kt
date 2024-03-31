package dev.babananick.pap.tests

import dev.babananick.pap.questions.QuestionWithVariants

data class PersonalTestWithLeadScale(
    override val questions: List<QuestionWithVariants>
): PersonalTest