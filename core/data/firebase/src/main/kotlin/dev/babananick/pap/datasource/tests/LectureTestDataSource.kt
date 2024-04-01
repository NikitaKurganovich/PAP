package dev.babananick.pap.datasource.tests

import dev.babananick.pap.tests.TestWithRightAnswer
import kotlinx.coroutines.flow.Flow

interface LectureTestDataSource {
    fun receiveLectureQuestions(moduleName: String, submoduleName: String): Flow<TestWithRightAnswer>

}