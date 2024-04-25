package dev.babananick.pap.core.data.repository

import dev.babananick.pap.core.data.firebase.datasource.lecture.LecturesDataSourceImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LecturesRepositoryImpl @Inject constructor(
    private val dataSource: LecturesDataSourceImpl,
) : LecturesRepository {
    override fun receiveLectureTheory( submoduleName: String): Flow<String> =
        dataSource.receiveLectureTheory( submoduleName)

}