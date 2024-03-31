package dev.babananick.pap.datasource.profile

import kotlinx.coroutines.flow.Flow

interface ProfileDataSource {
    fun receiveProfileData(): Flow<>
}