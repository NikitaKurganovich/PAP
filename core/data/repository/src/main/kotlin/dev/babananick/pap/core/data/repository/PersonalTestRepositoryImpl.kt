package dev.babananick.pap.core.data.repository

import dev.babananick.pap.core.data.firebase.datasource.tests.PersonalTestDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonalTestRepositoryImpl @Inject constructor(
    private val dataSource: PersonalTestDataSource,
) : PersonalTestRepository {
    override fun receiveTest(testId: String): Flow<dev.babananick.pap.core.model.tests.Test> =
        dataSource.receiveTest(
            testId = testId
        )
}