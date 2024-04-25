package dev.babananick.pap.core.util

import dev.babananick.pap.core.model.tests.Test
import dev.babananick.pap.core.model.tests.TestWithLeadScale
import dev.babananick.pap.core.model.tests.TestWithRightAnswer
import dev.babananick.pap.core.model.tests.TestWithSharedVariants

class TestAnalyzer(
    private val test: dev.babananick.pap.core.model.tests.Test,
) {

    fun prepareInterpretation(): List<PreparedInterpretation> {
        return when (test) {
            is dev.babananick.pap.core.model.tests.TestWithLeadScale -> {
                prepareWithLeadScaleInterpretation()
            }

            is dev.babananick.pap.core.model.tests.TestWithRightAnswer -> {
                listOf(prepareRightInterpretation())
            }

            is dev.babananick.pap.core.model.tests.TestWithSharedVariants -> {
                prepareSharedVariantsInterpretation()
            }

            else -> {
                listOf()
            }
        }

    }

    private fun prepareRightInterpretation(): PreparedInterpretation {
        test as dev.babananick.pap.core.model.tests.TestWithRightAnswer
        var answeredCorrectly = 0
        test.questions!!.forEach { question ->
            if (question.isAnsweredCorrectly) {
                answeredCorrectly++
            }
        }
        return PreparedInterpretation(
            "Всего правильных ответов",
            "$answeredCorrectly из ${test.questions!!.size}"
        )
    }

    private fun prepareSharedVariantsInterpretation(): List<PreparedInterpretation> {
        test as dev.babananick.pap.core.model.tests.TestWithSharedVariants
        val leadScalesToScore = test.interpretation!!.associate {
            it.lead_scale!! to 0L
        }.toMutableMap()
        val answerMap = test.answer_variants!!.associate {
            it.answer!! to it.related_score!!
        }
        test.questions!!.forEach {
            leadScalesToScore[it.related_scale!!] =
                leadScalesToScore[it.related_scale!!]!! + answerMap[it.currentSelected!!]!!
        }

        val interpretations = mutableListOf<PreparedInterpretation>()
        test.interpretation!!.forEach { interpretation ->
            interpretation.results!!.forEach { range ->
                if (range.range!!.isInRange(leadScalesToScore[interpretation.lead_scale]!!)) {
                    interpretations.add(
                        PreparedInterpretation(
                            "${interpretation.lead_scale}",
                            range.result!!
                        )
                    )
                }


            }
        }
        return interpretations
    }

    private fun prepareWithLeadScaleInterpretation(): List<PreparedInterpretation> {
        test as dev.babananick.pap.core.model.tests.TestWithLeadScale
        val leadScalesToScore = test.interpretation!!.associate {
            it.lead_scale!! to 0L
        }.toMutableMap()

        test.questions!!.forEach { question ->
            leadScalesToScore[question.scale!!] = leadScalesToScore[question.scale!!]!! + 1
        }
        val mostScores = leadScalesToScore.maxByOrNull { it.value }
        val leadScalesToMessage = test.interpretation!!.associate {
            it.lead_scale to it.result
        }
        val interpretations = mostScores.let {
            PreparedInterpretation(
                message = it!!.key,
                result = leadScalesToMessage[it.key]!!
            )
        }

        return listOf(interpretations)
    }

}