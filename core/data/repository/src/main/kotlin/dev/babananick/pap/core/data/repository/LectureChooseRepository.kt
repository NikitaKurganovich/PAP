package dev.babananick.pap.core.data.repository

import kotlinx.coroutines.flow.Flow

interface LectureChooseRepository {
    fun receiveLectureModules(): Flow<List<dev.babananick.pap.core.model.modules.LectureModule>>

}