package dev.babananick.pap.core.domain

import dev.babananick.pap.core.data.repository.LectureChooseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LectureChooseInteractor @Inject constructor(
    private val lectures: LectureChooseRepository,
) {
    fun receiveLectureModules(): Flow<List<dev.babananick.pap.core.model.modules.LectureModule>> =
        lectures.receiveLectureModules()
}