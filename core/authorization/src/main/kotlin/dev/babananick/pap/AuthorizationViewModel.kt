package dev.babananick.pap

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

class AuthorizationViewModel : ViewModel() {

    val chosenScreen = MutableStateFlow(AuthScreens.LOGIN)
    val prikol = MutableStateFlow(true)

    val state = combine(
        chosenScreen
    ) { screen ->
        when (screen.last()) {
            AuthScreens.LOGIN -> {
                AuthorizationScreenState.Login {
                    chosenScreen.update { AuthScreens.REGISTRATION }
                }
            }

            AuthScreens.REGISTRATION -> {
                AuthorizationScreenState.Registration {
                    chosenScreen.update { AuthScreens.LOGIN }
                }
            }
        }
    }.stateIn(viewModelScope,SharingStarted.WhileSubscribed(), AuthScreens.LOGIN)

    sealed class AuthorizationScreenState {
        data class Registration(val onClick: () -> Unit) : AuthorizationScreenState()
        data class Login(val onClick: () -> Unit) : AuthorizationScreenState()
    }

    enum class AuthScreens {
        LOGIN, REGISTRATION
    }
}