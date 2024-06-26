package dev.babananick.pap

import dev.babananick.pap.interpretation.Interpretation
import dev.babananick.pap.tests.Test

sealed class TestState {
    data class ShowTest(val data: Test) : TestState()
    data class ShowResults(val analyzer: TestAnalyzer): TestState()
    data class Base(val states: ScreenStates) : TestState()
}