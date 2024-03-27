package dev.babananick.pap.datasource

import kotlinx.coroutines.flow.Flow

interface PersonalTestChooseDataSource {
    val personalTests: Flow<List<String>>
    fun receiveTests(): Flow<List<String>>
}