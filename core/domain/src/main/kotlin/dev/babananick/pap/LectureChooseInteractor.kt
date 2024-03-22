package dev.babananick.pap

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LectureChooseInteractor @Inject constructor(
    private val lectures: LectureChooseRepository,
) {
    fun receiveLectureModules(): Flow<List<LectureModule>> =
        lectures.receiveLectureModules()
}