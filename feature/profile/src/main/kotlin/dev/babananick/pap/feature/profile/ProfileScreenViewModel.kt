package dev.babananick.pap.feature.profile

import androidx.annotation.AnyThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.babananick.pap.core.common.ScreenStates
import kotlinx.coroutines.flow.*

class ProfileScreenViewModel : ViewModel() {
    private val loading = MutableStateFlow(false)
    val emptyData: HashMap<String, HashMap<String, Int>> = hashMapOf()

    val state: StateFlow<ProfileState> = loading
        .map(::fakeStates)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            ProfileState.Base(ScreenStates.Loading)
        )

    @AnyThread
    private fun fakeStates(loading: Boolean): ProfileState =
        when {
            loading -> ProfileState.Base(ScreenStates.Loading)
            else -> ProfileState.ShowingResults(emptyData)
        }

}
