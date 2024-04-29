package dev.babananick.pap.core.data.firebase.datasource.tests

import dev.babananick.pap.core.model.modules.TestModule
import kotlinx.coroutines.flow.Flow

interface PersonalTestChooseDataSource {
    fun receiveTests(): Flow<List<TestModule>>
}