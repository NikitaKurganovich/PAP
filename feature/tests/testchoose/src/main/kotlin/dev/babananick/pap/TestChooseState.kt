package dev.babananick.pap

import dev.babananick.pap.modules.TestModule

sealed class TestChooseState{
    data class ShowingTestsChoose(val tests: List<TestModule>): TestChooseState()
    data class Base(val state: ScreenStates): TestChooseState()

}