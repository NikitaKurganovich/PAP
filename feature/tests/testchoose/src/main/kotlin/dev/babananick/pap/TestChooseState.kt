package dev.babananick.pap

sealed class TestChooseState{
    data class ShowingTestsChoose(val tests: List<String>): TestChooseState()
    data class Base(val state: ScreenStates): TestChooseState()

}