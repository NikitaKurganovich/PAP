package com.example.pap.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.pap.util.CustomButton
import com.example.pap.util.DefaultText
import com.example.pap.vm.TestScreenViewModel
import com.example.pap.vm.TestState
import kotlinx.coroutines.flow.update

class TestScreen : Screen {
    @Composable
    override fun Content() {
        val testVM: TestScreenViewModel = viewModel()
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
                is TestState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }

                is TestState.ShowingPersonalTests -> {
                    val data = (state as TestState.ShowingPersonalTests).data
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(data) { text ->
                            DefaultText(text, Modifier.clickable {
                                if (testVM.isResultsExist()) {
                                    isDialogOnRewriteOpen.value = true
                                } else {
                                    testVM.isChosen.update { true }
                                }

                            })
                        }
                    }
                }

                is TestState.Empty -> {
                    Text("Пусто")
                }

                is TestState.Error -> {
                    val error = (state as TestState.Error).e
                    Text("Error: ${error.message}")
                }

                is TestState.EmotionalIntelligence -> {
                    val data = (state as TestState.EmotionalIntelligence).data
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(data) {
                            it.QuestionElement()
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        item {
                            CustomButton(
                                onClick = {
                                    val map = mutableMapOf<String, Int>()
                                    data.forEach {
                                        if (map[it.related_scale] == null) {
                                            map[it.related_scale] = it.answeredScore
                                        } else {
                                            map[it.related_scale] = map[it.related_scale]!! + it.answeredScore
                                        }

                                        if (!it.isAnswered) {
                                            isDialogOnNotFullOpen.value = true
                                        } else {
                                            testVM.userResults = map
                                            isDialogOnConfirmOpen.value = true
                                        }
                                    }
                                },
                                modifier = Modifier.fillParentMaxWidth(0.76f)
                            )
                        }
                    }
                }

                is TestState.ShowingResults -> {
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
        viewModel: TestScreenViewModel,
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
        testVM: TestScreenViewModel
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