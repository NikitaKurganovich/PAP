package dev.babananick.pap.core.data.firebase.datasource.profile

import dev.babananick.pap.core.model.ProfileData
import kotlinx.coroutines.flow.Flow

interface ProfileDataSource {
    fun receiveProfileData(): Flow<dev.babananick.pap.core.model.ProfileData>
}