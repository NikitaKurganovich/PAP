package dev.babananick.pap

import dev.babananick.pap.tests.Test
import kotlinx.coroutines.flow.Flow

interface PersonalTestRepository {
    fun receiveTest(testId: String): Flow<Test>

}