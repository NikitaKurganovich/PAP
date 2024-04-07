package dev.babananick.pap

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.tests.TestResult

@Immutable
@IgnoreExtraProperties
data class LectureResult(
    val lecture_name: String? = null,
    val test_result: TestResult? = null
    )
