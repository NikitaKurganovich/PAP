package dev.babananick.pap.feature.academic.lecturechoose

import dev.babananick.pap.core.common.ScreenStates

sealed class HomeState {
    data class ShowingModules(val data: List<dev.babananick.pap.core.model.modules.LectureModule>) : HomeState()

    data class Base(val state: ScreenStates): HomeState()
}