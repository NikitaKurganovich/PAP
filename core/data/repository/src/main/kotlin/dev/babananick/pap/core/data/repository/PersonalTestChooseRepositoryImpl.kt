package dev.babananick.pap.core.data.repository

import dev.babananick.pap.core.data.firebase.datasource.tests.PersonalTestChooseDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonalTestChooseRepositoryImpl @Inject constructor(
    private val dataSource: PersonalTestChooseDataSource,
) : PersonalTestChooseRepository {
    override fun receiveTests(): Flow<List<dev.babananick.pap.core.model.modules.TestModule>> = dataSource.receiveTests()
}