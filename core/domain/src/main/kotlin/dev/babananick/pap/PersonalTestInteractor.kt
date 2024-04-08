package dev.babananick.pap

import dev.babananick.pap.tests.Test
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonalTestInteractor @Inject constructor(
    private val repository: PersonalTestRepository,
) {
    fun receivePersonalTest(testName: String): Flow<Test> =
        repository.receiveTest(testName)
}