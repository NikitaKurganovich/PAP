package dev.babananick.pap

import dev.babananick.pap.datasource.lecture.LectureChooseDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LectureChooseRepositoryImpl @Inject constructor(
   private val dataSource: LectureChooseDataSource
): LectureChooseRepository {
    override fun receiveLectureModules(): Flow<List<LectureModule>> {
        return dataSource.receiveLectureModules()
    }
}