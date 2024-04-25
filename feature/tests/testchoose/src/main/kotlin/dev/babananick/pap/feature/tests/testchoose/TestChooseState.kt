package dev.babananick.pap.feature.tests.testchoose

import dev.babananick.pap.core.common.ScreenStates

sealed class TestChooseState{
    data class ShowingTestsChoose(val tests: List<dev.babananick.pap.core.model.modules.TestModule>): TestChooseState()
    data class Base(val state: ScreenStates): TestChooseState()

}