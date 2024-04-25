package dev.babananick.pap.core.data.repository

import kotlinx.coroutines.flow.Flow

interface PersonalTestChooseRepository {
    fun receiveTests(): Flow<List<dev.babananick.pap.core.model.modules.TestModule>>

}