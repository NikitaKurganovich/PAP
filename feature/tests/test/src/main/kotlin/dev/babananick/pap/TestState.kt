package dev.babananick.pap

import dev.babananick.pap.tests.Test

sealed class TestState {
    data class ShowTest(val data: Test) : TestState()
    data class ShowResults(val interpretation: List<Interpretation>): TestState()
    data class Base(val states: ScreenStates) : TestState()
}