package dev.babananick.pap

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.flow.update

class TestResultScreen : Screen {
    @Composable
    override fun Content() {
        val testVM: TestScreenResultViewModel = hiltViewModel()
        val state by testVM.state.collectAsState()
        val isDialogOnNotFullOpen = remember { mutableStateOf(false) }
        val isDialogOnConfirmOpen = remember { mutableStateOf(false) }
        val isDialogOnRewriteOpen = remember { mutableStateOf(false) }
        ConfirmAnswersDialog(isDialogOnConfirmOpen, testVM)
        NotFullAnswerDialog(isDialogOnNotFullOpen)
        AlertOnConfirmRewrite(isDialogOnRewriteOpen, testVM)
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when (state) {
                is TestResultState.ShowingResults -> {
                    val data = (state as TestState.ShowingResults).score
                    LazyColumn {
                        items(data.map { it.toPair() }) { element ->
                            Column {
                                DefaultText(
                                    "${element.first} - ${element.second} балл(-ов) (${
                                        if (element.second > 13) "Высокий"
                                        else if (element.second > 7) "Средний" else "Низкий"
                                    })"
                                )
                                Spacer(Modifier.height(4.dp))
                                DefaultText(theoryMap[element.first] ?: "")
                                Spacer(Modifier.height(20.dp))
                            }
                        }
                    }

                }

                is
            }
        }
    }

    val theoryMap = mapOf(
        "Эмоциональная осведомленность" to "осознание и понимание своих эмоций, а для этого постоянное пополнение собственного словаря эмоций. Люди с высокой эмоциональной осведомленностью в большей мере, чем у другие осведомлены о своем внутреннем состоянии",
        "Управление эмоциями" to "эмоциональная отходчивость, эмоциональная гибкость и т.д., другими словами, произвольное управление своими эмоциями",
        "Самомотивация" to "управление своим поведением, за счет управления эмоциями",
        "Эмпатия" to "понимание эмоций других людей, умение сопереживать текущему эмоциональному состоянию другого человека, а так же готовность оказать поддержку. Это умение понять состояние человека по мимике, жестам, оттенкам речи, позе",
        "Распознавание эмоций других людей" to "умение воздействовать на эмоциональное состояние других людей"
    )

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
        viewModel: TestScreenResultViewModel,
    ) {
        if (isDialogOnConfirmOpen.value) {
            AlertDialog(
                onDismissRequest = {},
                title = { Text("Проверить результаты?") },
                text = { Text("Вы уверены, что хотите отправить тест на проверку?") },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.upsertTestResult(
                            "Эмоциональный интеллект"
                        )
                        viewModel.isResultsSend.update { true }
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

    @Composable
    fun AlertOnConfirmRewrite(
        isShown: MutableState<Boolean>,
        testVM: TestScreenResultViewModel
    ) {
        if (isShown.value) {
            AlertDialog(
                onDismissRequest = {isShown.value = false},
                title = { Text("Перепройти тест?") },
                text = { Text("Вы уверены, что хотите перепройти тест? Вы ранее его проходили") },
                confirmButton = {
                    TextButton(onClick = {
                        testVM.isChosen.update { true }
                        isShown.value = false
                    }) {
                        Text("Да")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { isShown.value = false }) {
                        Text("Отмена")
                    }
                }
            )
        }
    }
}