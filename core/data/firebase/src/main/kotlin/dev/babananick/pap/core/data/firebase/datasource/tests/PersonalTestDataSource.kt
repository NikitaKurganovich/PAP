package dev.babananick.pap.core.data.firebase.datasource.tests

import dev.babananick.pap.core.model.tests.Test
import kotlinx.coroutines.flow.Flow

interface PersonalTestDataSource {
    fun receiveTest(testId: String): Flow<Test>

}