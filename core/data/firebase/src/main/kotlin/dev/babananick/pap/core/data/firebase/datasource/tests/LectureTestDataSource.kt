package dev.babananick.pap.core.data.firebase.datasource.tests

import dev.babananick.pap.core.model.tests.TestWithRightAnswer
import kotlinx.coroutines.flow.Flow

interface LectureTestDataSource {
    fun receiveLectureQuestions(submoduleName: String): Flow<dev.babananick.pap.core.model.tests.TestWithRightAnswer>

}