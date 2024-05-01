package dev.babananick.pap.core.data.firebase.datasource.lecture

import dev.babananick.pap.core.model.modules.AcademicModule
import kotlinx.coroutines.flow.Flow

interface LectureChooseDataSource {
    fun receiveLectureModules(): Flow<List<AcademicModule>>

}