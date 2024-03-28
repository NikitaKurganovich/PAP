package dev.babananick.pap

sealed class ProfileState {
    data class ShowingResults(val data: HashMap<String, HashMap<String, Int>>) : ProfileState()

    data class Base(val state: ScreenStates): ProfileState()
}