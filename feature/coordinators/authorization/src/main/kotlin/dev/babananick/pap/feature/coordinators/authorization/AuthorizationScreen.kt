package dev.babananick.pap.feature.coordinators.authorization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import dev.babananick.pap.RegistrationScreen
import dev.babananick.pap.feature.login.LoginScreen

class AuthorizationScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: AuthorizationViewModel = viewModel()
        val currentScreen by viewModel.state.collectAsState()

        when (currentScreen) {
            is AuthorizationViewModel.ScreenState.Login -> {
                val onClick =
                    (currentScreen as AuthorizationViewModel.ScreenState.Login).onClick
                LoginScreen(onClick)
            }

            is AuthorizationViewModel.ScreenState.Registration -> {
                val onClick =
                    (currentScreen as AuthorizationViewModel.ScreenState.Registration).onClick
                RegistrationScreen(onClick)
            }
        }
    }
}