package dev.babananick.pap.datasource.tests

import kotlinx.coroutines.flow.Flow

interface PersonalTestChooseDataSource {
    fun receiveTests(): Flow<List<String>>
}