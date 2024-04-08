package dev.babananick.pap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import coil.ImageLoader
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

data class ProfileScreen @Inject constructor(
    private val imageLoader: ImageLoader,
) : Screen {
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
                    val data = (screenState as ProfileState.ShowingResults).data
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            DefaultText(
                                "Ваши результаты, ${Firebase.auth.currentUser?.email}",
                                textAlign = TextAlign.Center
                            )
                            Spacer(Modifier.height(20.dp))
                        }
                        items(data.map { it.toPair() }) { item ->
                            if (item.first != "Эмоциональный интеллект") {
                                Box(
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.secondary)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    DefaultText("Модуль ${item.first}", textAlign = TextAlign.Center)
                                }

                                Spacer(Modifier.height(10.dp))
                                item.second.forEach { (s, i) ->
                                    DefaultText("$s \n $i балл(-ов)", textAlign = TextAlign.Center)
                                }
                                Spacer(Modifier.height(20.dp))
                            } else {
                                Box(
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.secondary)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    DefaultText(item.first)
                                }
                                Spacer(Modifier.height(10.dp))
                                item.second.forEach { element ->
                                    DefaultText(
                                        "${element.key} \n ${element.value} балл(-ов) (${
                                            if (element.value > 13) "Высокий"
                                            else if (element.value > 7) "Средний" else "Низкий"
                                        })",
                                        textAlign = TextAlign.Center
                                    )
                                    Spacer(Modifier.height(20.dp))
                                }

                            }
                        }
                        item {
                            CustomButton("Выйти", onClick = { isOpen.value = true })

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
