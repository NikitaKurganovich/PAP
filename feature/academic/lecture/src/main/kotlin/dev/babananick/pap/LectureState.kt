package dev.babananick.pap

sealed class LectureState{
    data class ShowingTheory(val data: String) : LectureState()

    data class Base(val state: ScreenStates): LectureState()
}