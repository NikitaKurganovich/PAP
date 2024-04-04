package dev.babananick.pap

import dev.babananick.pap.datasource.tests.PersonalTestChooseDataSource
import dev.babananick.pap.modules.TestModule
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonalTestChooseRepositoryImpl @Inject constructor(
    private val dataSource: PersonalTestChooseDataSource,
) : PersonalTestChooseRepository {
    override fun receiveTests(): Flow<List<TestModule>> = dataSource.receiveTests()
}