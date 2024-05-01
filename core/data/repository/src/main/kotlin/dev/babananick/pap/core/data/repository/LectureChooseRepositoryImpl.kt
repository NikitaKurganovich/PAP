package dev.babananick.pap.core.data.repository

import dev.babananick.pap.core.data.firebase.datasource.lecture.LectureChooseDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LectureChooseRepositoryImpl @Inject constructor(
   private val dataSource: LectureChooseDataSource
): LectureChooseRepository {
    override fun receiveLectureModules(): Flow<List<dev.babananick.pap.core.model.modules.AcademicModule>>
        = dataSource.receiveLectureModules()

}