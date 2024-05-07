package dev.babananick.pap.feature.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.babananick.pap.core.common.BaseScreenStateValues

class ProfileScreen : Screen {
    @Composable
    override fun Content() {

        val profileVM: ProfileScreenViewModel = viewModel()
        val screenState by profileVM.state.collectAsState()
        val isOpen = remember { mutableStateOf(false) }
        ConfirmLogoutDialog(isOpen)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (screenState) {
                is ProfileState.ShowingResults -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                Firebase.auth.signOut()
                            }) {
                            Text(
                                text = "Выйти",
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }

                is ProfileState.Base -> {
                    BaseScreenStateValues((screenState as ProfileState.Base).state)
                }
            }
        }
    }

    @Composable
    fun ConfirmLogoutDialog(
        isDialogOnConfirmOpen: MutableState<Boolean>,
    ) {
        if (isDialogOnConfirmOpen.value) {
            AlertDialog(
                onDismissRequest = {},
                title = { Text("Выйти из аккаунта?") },
                text = { Text("Вы уверены, что хотите выйти из текущего аккаунта? Это действие нельзя отменить. После выхода, вам нужно будет заново авторизироваться, для прохождения тестов") },
                confirmButton = {
                    TextButton(onClick = {
                        isDialogOnConfirmOpen.value = false
                        Firebase.auth.signOut()
                    }) {
                        Text("Да")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { isDialogOnConfirmOpen.value = false }) {
                        Text("Отмена")
                    }
                }
            )
        }
    }
}
