package dev.babananick.pap.core.model

import androidx.compose.runtime.Immutable
import com.google.firebase.database.IgnoreExtraProperties
import dev.babananick.pap.core.model.tests.TestResult

@Immutable
@IgnoreExtraProperties
data class LectureResult(
    val lecture_name: String? = null,
    val test_result: dev.babananick.pap.core.model.tests.TestResult? = null
    )
