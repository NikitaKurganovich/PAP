package dev.babananick.pap

import kotlinx.coroutines.flow.Flow

interface PersonalTestChooseRepository {
    fun receiveTests(): Flow<List<String>>

}