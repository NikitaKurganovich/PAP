package dev.babananick.pap

import dev.babananick.pap.modules.TestModule
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonalTestChooseInteractor @Inject constructor(
    private val tests: PersonalTestChooseRepository,
) {
    fun receivePersonalTestNames(): Flow<List<TestModule>> =
        tests.receiveTests()
}