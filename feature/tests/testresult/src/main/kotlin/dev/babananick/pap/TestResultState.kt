package dev.babananick.pap

sealed class TestResultState {
    data class ShowingResults(val score: HashMap<String, Int>) : TestResultState()
    data class Base(val states: ScreenStates): TestResultState()
}