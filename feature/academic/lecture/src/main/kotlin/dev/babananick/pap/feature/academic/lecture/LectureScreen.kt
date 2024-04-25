package dev.babananick.pap.feature.academic.lecture

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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.babananick.pap.core.common.BaseScreenStateValues
import dev.babananick.pap.ui.components.CustomButton
import dev.babananick.pap.ui.theme.montserratFontFamily

data class LectureScreen(
    private val moduleName: String,
    private val submoduleName: String,
    private val theory: String
) : Screen {
    @Composable
    override fun Content() {
        val currentVM: LectureScreenViewModel =
            viewModel(factory = LectureScreenViewModelFactory(moduleName, submoduleName, theory))
        val navigator = LocalNavigator.currentOrThrow
        val isDialogOnNotFullOpen = remember { mutableStateOf(false) }
        val isDialogOnConfirmOpen = remember { mutableStateOf(false) }
        val screenState by currentVM.state.collectAsState()
        ConfirmAnswersDialog(isDialogOnConfirmOpen, currentVM)
        NotFullAnswerDialog(isDialogOnNotFullOpen)
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when (screenState) {
                is LectureState.ShowingTheory -> {
                    val data = (screenState as LectureState.ShowingTheory).data

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(listOf(data)) { text ->
                            Text(text = text, fontFamily = dev.babananick.pap.ui.theme.montserratFontFamily, textAlign = TextAlign.Justify)
                        }
                        item {
                            Spacer(Modifier.height(5.dp))
                            CustomButton(
                                prompt = "Пройти тест по лекции",
                                onClick = {

                                },
                                modifier = Modifier.height(50.dp)
                            )
                        }
                    }
                }

                is LectureState.Base ->{
                    BaseScreenStateValues((screenState as LectureState.Base).state)
                }
            }
        }

    }

    @Composable
    fun NotFullAnswerDialog(
        isDialogOnNotFullOpen: MutableState<Boolean>,
    ) {
        if (isDialogOnNotFullOpen.value) {
            AlertDialog(
                onDismissRequest = { isDialogOnNotFullOpen.value = false },
                title = { Text("Пропущены вопросы!") },
                text = { Text("Пожалуйста, удостовереть что вы ответили на все вопросы перед подтверждением проверки") },
                confirmButton = {
                    TextButton(onClick = {
                        isDialogOnNotFullOpen.value = false
                    }) {
                        Text("ОК")
                    }
                }
            )
        }
    }

    @Composable
    fun ConfirmAnswersDialog(
        isDialogOnConfirmOpen: MutableState<Boolean>,
        viewModel: LectureScreenViewModel,
    ) {
        if (isDialogOnConfirmOpen.value) {
            AlertDialog(
                onDismissRequest = {},
                title = { Text("Проверить результаты?") },
                text = { Text("Вы уверены, что хотите отправить тест на проверку?") },
                confirmButton = {
                    TextButton(onClick = {
                        isDialogOnConfirmOpen.value = false
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