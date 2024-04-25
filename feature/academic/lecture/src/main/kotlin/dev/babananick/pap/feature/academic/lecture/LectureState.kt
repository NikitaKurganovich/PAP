package dev.babananick.pap.feature.academic.lecture

import dev.babananick.pap.core.common.ScreenStates

sealed class LectureState{
    data class ShowingTheory(val data: String) : LectureState()

    data class Base(val state: ScreenStates): LectureState()
}