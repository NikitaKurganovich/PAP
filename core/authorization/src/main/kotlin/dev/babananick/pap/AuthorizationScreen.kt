package dev.babananick.pap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen

class AuthorizationScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: AuthorizationViewModel = viewModel()
        val currentScreen by viewModel.state.collectAsState()

        when (currentScreen) {
            is AuthorizationViewModel.AuthorizationScreenState.Login -> {
                val onClick =
                    (currentScreen as AuthorizationViewModel.AuthorizationScreenState.Login).onClick
                LoginScreen(onClick)
            }

            is AuthorizationViewModel.AuthorizationScreenState.Registration -> {
                val onClick =
                    (currentScreen as AuthorizationViewModel.AuthorizationScreenState.Registration).onClick
                RegistrationScreen(onClick)
            }
        }
    }
}