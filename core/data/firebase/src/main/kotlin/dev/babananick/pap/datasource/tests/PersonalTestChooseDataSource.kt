package dev.babananick.pap.datasource.tests

import dev.babananick.pap.modules.TestModule
import kotlinx.coroutines.flow.Flow

interface PersonalTestChooseDataSource {
    fun receiveTests(): Flow<List<TestModule>>
}