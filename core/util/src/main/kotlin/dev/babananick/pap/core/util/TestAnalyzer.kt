package dev.babananick.pap.core.util

import android.content.res.Resources
import dev.babananick.pap.core.model.tests.Test
import dev.babananick.pap.core.model.tests.TestMBTI
import dev.babananick.pap.core.model.tests.TestWithLeadScale
import dev.babananick.pap.core.model.tests.TestWithRightAnswer
import dev.babananick.pap.core.model.tests.TestWithSharedVariants

class TestAnalyzer(
    private val test: Test,
) {

    fun prepareInterpretation(): List<PreparedInterpretation> {
        return when (test) {
            is TestWithLeadScale -> {
                prepareWithLeadScaleInterpretation()
            }

            is TestWithRightAnswer -> {
                listOf(prepareRightInterpretation())
            }

            is TestWithSharedVariants -> {
                prepareSharedVariantsInterpretation()
            }

            is TestMBTI -> {
                prepareMBTIInterpretation()
            }

            else -> {
                listOf()
            }
        }

    }

    private fun prepareRightInterpretation(): PreparedInterpretation {
        test as TestWithRightAnswer
        var answeredCorrectly = 0
        test.questions!!.forEach { question ->
            if (question.isAnsweredCorrectly) {
                answeredCorrectly++
            }
        }
        return PreparedInterpretation(
            message = Resources.getSystem().getString(R.string.results_with),
            result = "$answeredCorrectly ${
                Resources.getSystem().getString(R.string.result_out)
            } ${test.questions!!.size}"
        )
    }

    private fun prepareSharedVariantsInterpretation(): List<PreparedInterpretation> {
        test as TestWithSharedVariants
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
        test as TestWithLeadScale
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

    private fun prepareMBTIInterpretation(): List<PreparedInterpretation> {
        test as TestMBTI
        val answers = mutableListOf<String>()
        test.questions!!.forEach {
            answers.add(it.currentSelected!!)
        }
        val targetType = calculateMBTI(answers)
        val actualType = test.interpretation!!.find {
            it.type == targetType
        }

        return listOf(
            PreparedInterpretation(
                result = actualType!!.type,
                message = actualType.description
            )
        )
    }

    private fun calculateMBTI(answers: List<String>): String {
        var e = 0
        var i = 0
        var n = 0
        var s = 0
        var t = 0
        var f = 0
        var j = 0
        var p = 0

        for (answer in answers) {
            when (answer) {
                "E" -> e++
                "I" -> i++
                "N" -> n++
                "S" -> s++
                "T" -> t++
                "F" -> f++
                "J" -> j++
                "P" -> p++
            }
        }

        val personalityType =
            "${if (e > i) "E" else "I"}${if (n > s) "N" else "S"}${if (t > f) "T" else "F"}${if (j > p) "J" else "P"}"
        return personalityType
    }
}