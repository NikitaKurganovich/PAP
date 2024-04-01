package dev.babananick.pap

import dev.babananick.pap.datasource.tests.PersonalTestDataSource
import dev.babananick.pap.tests.Test
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonalTestRepositoryImpl @Inject constructor(
    private val dataSource: PersonalTestDataSource,
) : PersonalTestRepository {
    override fun receiveTest(testName: String): Flow<Test> =
        dataSource.receiveTest(testName = testName)

}