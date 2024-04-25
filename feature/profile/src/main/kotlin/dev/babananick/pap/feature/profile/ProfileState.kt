package dev.babananick.pap.feature.profile

import dev.babananick.pap.core.common.ScreenStates

sealed class ProfileState {
    data class ShowingResults(val data: HashMap<String, HashMap<String, Int>>) : ProfileState()

    data class Base(val state: ScreenStates): ProfileState()
}