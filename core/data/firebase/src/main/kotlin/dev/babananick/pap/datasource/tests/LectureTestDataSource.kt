package dev.babananick.pap.datasource.tests

import dev.babananick.pap.questions.LectureWithRightAnswer
import kotlinx.coroutines.flow.Flow

interface LectureTestDataSource {
    fun receiveLectureQuestions(moduleName: String, submoduleName: String): Flow<List<LectureWithRightAnswer>>

}