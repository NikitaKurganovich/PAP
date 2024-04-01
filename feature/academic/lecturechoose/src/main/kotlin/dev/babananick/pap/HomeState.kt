package dev.babananick.pap

sealed class HomeState {
    data class ShowingModules(val data: List<LectureModule>) : HomeState()

    data class Base(val state: ScreenStates): HomeState()
}