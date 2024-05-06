package dev.babananick.pap.core.data.repository

import dev.babananick.pap.core.model.tests.Test
import kotlinx.coroutines.flow.Flow

interface PersonalTestRepository {
    fun receiveTest(testId: String): Flow<Test>

}