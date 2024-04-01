package dev.babananick.pap

import dev.babananick.pap.datasource.tests.LectureTestDataSourceImpl
import dev.babananick.pap.tests.TestWithRightAnswer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LectureTestRepositoryImpl @Inject constructor(
    private val dataSource: LectureTestDataSourceImpl
) : LectureTestRepository {
    override fun receiveLectureQuestions(moduleName: String, submoduleName: String): Flow<TestWithRightAnswer> {
        return dataSource.receiveLectureQuestions(moduleName, submoduleName)
    }

}