package dev.babananick.pap.core.data.repository

import dev.babananick.pap.core.data.firebase.datasource.tests.LectureTestDataSourceImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LectureTestRepositoryImpl @Inject constructor(
    private val dataSource: LectureTestDataSourceImpl
) : LectureTestRepository {
    override fun receiveLectureQuestions(submoduleName: String): Flow<dev.babananick.pap.core.model.tests.TestWithRightAnswer> {
        return dataSource.receiveLectureQuestions( submoduleName)
    }

}