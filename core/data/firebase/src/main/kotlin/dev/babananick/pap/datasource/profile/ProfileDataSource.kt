package dev.babananick.pap.datasource.profile

import dev.babananick.pap.ProfileData
import kotlinx.coroutines.flow.Flow

interface ProfileDataSource {
    fun receiveProfileData(): Flow<ProfileData>
}