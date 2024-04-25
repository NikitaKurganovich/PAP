package dev.babananick.pap.core.domain

import dev.babananick.pap.core.data.repository.PersonalTestChooseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonalTestChooseInteractor @Inject constructor(
    private val tests: PersonalTestChooseRepository,
) {
    fun receivePersonalTests(): Flow<List<dev.babananick.pap.core.model.modules.TestModule>> =
        tests.receiveTests()
}