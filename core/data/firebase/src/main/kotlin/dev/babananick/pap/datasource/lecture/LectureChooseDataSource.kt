package dev.babananick.pap.datasource.lecture

import dev.babananick.pap.modules.LectureModule
import kotlinx.coroutines.flow.Flow

interface LectureChooseDataSource {
    fun receiveLectureModules(): Flow<List<LectureModule>>

}