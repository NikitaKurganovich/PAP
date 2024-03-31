package dev.babananick.pap

import dev.babananick.pap.questions.LectureWithRightAnswer
import kotlinx.coroutines.flow.Flow

interface LectureTestRepository {
    fun receiveLectureQuestions(moduleName: String, submoduleName: String): Flow<List<LectureWithRightAnswer>>

}