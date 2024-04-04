package dev.babananick.pap

import dev.babananick.pap.tests.TestWithRightAnswer
import kotlinx.coroutines.flow.Flow

interface LectureTestRepository {
    fun receiveLectureQuestions( submoduleName: String): Flow<TestWithRightAnswer>

}