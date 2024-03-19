package dev.babananick.pap

import dev.babananick.pap.LectureQuestion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LectureTestInteractor @Inject constructor(
    private val lectures: LectureTestRepository
) {
    suspend fun receiveLectureTest(moduleName:String, submoduleName: String): Flow<List<LectureQuestion>> {
        return lectures.receiveLectureQuestions( moduleName, submoduleName)
    }
}