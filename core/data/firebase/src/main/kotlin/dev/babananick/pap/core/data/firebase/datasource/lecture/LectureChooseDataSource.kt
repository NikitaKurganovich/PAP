package dev.babananick.pap.core.data.firebase.datasource.lecture

import dev.babananick.pap.core.model.modules.LectureModule
import kotlinx.coroutines.flow.Flow

interface LectureChooseDataSource {
    fun receiveLectureModules(): Flow<List<LectureModule>>

}