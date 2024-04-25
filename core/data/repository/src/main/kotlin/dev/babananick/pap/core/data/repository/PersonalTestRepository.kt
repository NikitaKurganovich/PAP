package dev.babananick.pap.core.data.repository

import kotlinx.coroutines.flow.Flow

interface PersonalTestRepository {
    fun receiveTest(testId: String): Flow<dev.babananick.pap.core.model.tests.Test>

}