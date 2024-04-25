package dev.babananick.pap.feature.coordinators.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

class AuthorizationViewModel : ViewModel() {

    private val chosenScreen = MutableStateFlow(AuthScreens.LOGIN)

    val state = combine(
        chosenScreen
    ) { screen ->
        when (screen.last()) {
            AuthScreens.LOGIN -> {
                ScreenState.Login {
                    chosenScreen.update { AuthScreens.REGISTRATION }
                }
            }

            AuthScreens.REGISTRATION -> {
                ScreenState.Registration {
                    chosenScreen.update { AuthScreens.LOGIN }
                }
            }
        }
    }.stateIn(viewModelScope,SharingStarted.WhileSubscribed(), AuthScreens.LOGIN)

    sealed class ScreenState {
        data class Registration(val onClick: () -> Unit) : ScreenState()
        data class Login(val onClick: () -> Unit) : ScreenState()
    }
}