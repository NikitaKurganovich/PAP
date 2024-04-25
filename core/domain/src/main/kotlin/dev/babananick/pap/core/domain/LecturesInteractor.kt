package dev.babananick.pap.core.domain

import dev.babananick.pap.core.data.repository.LecturesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LecturesInteractor @Inject constructor(
    private val lectures: LecturesRepository
) {
    fun receiveLectureTheory(submoduleName: String): Flow<String> {
        return lectures.receiveLectureTheory(submoduleName)
    }
}