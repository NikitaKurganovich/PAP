package dev.babananick.pap

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonalTestChooseInteractor @Inject constructor(
    private val tests: PersonalTestChooseRepository,
) {
    fun receivePersonalTestNames(): Flow<List<String>> =
        tests.receiveTests()
}