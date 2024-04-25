package dev.babananick.pap.feature.tests.test

import dev.babananick.pap.core.common.ScreenStates
import dev.babananick.pap.core.util.TestAnalyzer

sealed class TestState {
    data class ShowTest(val data: dev.babananick.pap.core.model.tests.Test) : TestState()
    data class ShowResults(val analyzer: TestAnalyzer): TestState()
    data class Base(val states: ScreenStates) : TestState()
}