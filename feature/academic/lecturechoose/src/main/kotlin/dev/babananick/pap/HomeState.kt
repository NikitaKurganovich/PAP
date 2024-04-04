package dev.babananick.pap

import dev.babananick.pap.modules.LectureModule

sealed class HomeState {
    data class ShowingModules(val data: List<LectureModule>) : HomeState()

    data class Base(val state: ScreenStates): HomeState()
}