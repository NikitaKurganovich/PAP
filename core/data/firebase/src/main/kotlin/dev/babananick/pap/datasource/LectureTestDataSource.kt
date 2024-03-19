package dev.babananick.pap.datasource

import dev.babananick.pap.LectureQuestion
import kotlinx.coroutines.flow.Flow

interface LectureTestDataSource {
    fun receiveLectureQuestions(moduleName: String, submoduleName: String): Flow<List<LectureQuestion>>

}