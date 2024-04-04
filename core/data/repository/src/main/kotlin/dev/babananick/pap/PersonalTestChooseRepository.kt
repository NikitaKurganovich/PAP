package dev.babananick.pap

import dev.babananick.pap.modules.TestModule
import kotlinx.coroutines.flow.Flow

interface PersonalTestChooseRepository {
    fun receiveTests(): Flow<List<TestModule>>

}