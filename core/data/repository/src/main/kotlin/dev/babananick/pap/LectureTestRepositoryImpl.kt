package dev.babananick.pap

import dev.babananick.pap.datasource.LectureTestDataSourceImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LectureTestRepositoryImpl @Inject constructor(
    private val dataSource: LectureTestDataSourceImpl
) : LectureTestRepository {
    override fun receiveLectureQuestions(moduleName: String, submoduleName: String): Flow<List<LectureQuestion>> {
        return dataSource.receiveLectureQuestions(moduleName, submoduleName)
    }

}