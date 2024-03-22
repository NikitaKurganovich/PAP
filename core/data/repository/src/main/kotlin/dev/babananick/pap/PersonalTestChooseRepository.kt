package dev.babananick.pap

import kotlinx.coroutines.flow.Flow

interface PersonalTestChooseRepository {
    val personalTests: Flow<List<String>>
    fun receiveTests(): Flow<List<String>>

}