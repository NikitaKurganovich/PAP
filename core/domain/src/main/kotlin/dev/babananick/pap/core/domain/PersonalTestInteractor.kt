package dev.babananick.pap.core.domain

import dev.babananick.pap.core.data.repository.PersonalTestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonalTestInteractor @Inject constructor(
    private val repository: PersonalTestRepository,
) {
    fun receivePersonalTest(testId: String): Flow<dev.babananick.pap.core.model.tests.Test> =
        repository.receiveTest(testId)
}