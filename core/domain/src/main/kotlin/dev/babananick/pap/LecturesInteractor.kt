package dev.babananick.pap

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LecturesInteractor @Inject constructor(
    private val lectures: LecturesRepository
) {
    suspend fun receiveLectureTheory(moduleName: String, submoduleName: String): Flow<String> {
        return lectures.receiveLectureTheory(moduleName, submoduleName)
    }
}