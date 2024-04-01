package dev.babananick.pap

import dev.babananick.pap.datasource.lecture.LecturesDataSourceImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LecturesRepositoryImpl @Inject constructor(
    private val dataSource: LecturesDataSourceImpl,
) : LecturesRepository {
    override fun receiveLectureTheory(moduleName: String, submoduleName: String): Flow<String> =
        dataSource.receiveLectureTheory(moduleName, submoduleName)

}