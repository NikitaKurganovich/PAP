package dev.babananick.pap.datasource.tests

import dev.babananick.pap.tests.PersonalTest
import kotlinx.coroutines.flow.Flow

interface PersonalTestDataSource {
    fun receiveTest(testName: String): Flow<PersonalTest>

}