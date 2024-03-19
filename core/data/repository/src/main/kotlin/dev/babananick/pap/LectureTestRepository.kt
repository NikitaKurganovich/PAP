package dev.babananick.pap

import kotlinx.coroutines.flow.Flow

interface LectureTestRepository {
    fun receiveLectureQuestions(moduleName: String, submoduleName: String): Flow<List<LectureQuestion>>

}