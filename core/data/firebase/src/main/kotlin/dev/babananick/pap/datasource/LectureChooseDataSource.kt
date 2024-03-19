package dev.babananick.pap.datasource

import dev.babananick.pap.LectureModule
import kotlinx.coroutines.flow.Flow

interface LectureChooseDataSource {
    val lectureModules: Flow<List<LectureModule>>
    fun receiveLectureModules(): Flow<List<LectureModule>>

}