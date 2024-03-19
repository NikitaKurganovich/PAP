package dev.babananick.pap

import kotlinx.coroutines.flow.Flow

interface LectureChooseRepository {
    fun receiveLectureModules(): Flow<List<LectureModule>>

}