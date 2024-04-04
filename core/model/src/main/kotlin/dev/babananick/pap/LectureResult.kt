package dev.babananick.pap

import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.tests.TestResult

@IgnoreExtraProperties
data class LectureResult(
    val lecture_name: String? = null,
    val test_result: TestResult? = null
    )
