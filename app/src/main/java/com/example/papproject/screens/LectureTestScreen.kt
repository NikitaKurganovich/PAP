package com.example.papproject.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.papproject.util.CustomButton
import com.example.papproject.vm.LectureScreenViewModel
import com.example.papproject.vm.LectureTestState
import androidx.compose.material3.AlertDialog as AlertDialog1

data class LectureTestScreen(val currentVM: LectureScreenViewModel) : Screen {

    @Composable
    override fun Content() {
        val isDialogOnNotFullOpen = remember { mutableStateOf(false) }
        val isDialogOnConfirmOpen = remember { mutableStateOf(false) }
        val screenState by currentVM.questionState.collectAsState()
        ConfirmAnswersDialog(isDialogOnConfirmOpen)
        NotFullAnswerDialog(isDialogOnNotFullOpen)
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when (screenState) {
                is LectureTestState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }

                is LectureTestState.ShowingModules -> {
                    val data = (screenState as LectureTestState.ShowingModules).data
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
                                    var score = 0
                                    data.forEach {
                                        if (it.isAnsweredCorrectly) {
                                            score++
                                        }
                                        if (!it.isAnswered) {
                                            isDialogOnNotFullOpen.value = true
                                        } else {
                                            isDialogOnConfirmOpen.value = true
                                        }
                                    }
                                },
                                modifier = Modifier.fillParentMaxWidth(0.76f)
                            )
                        }
                    }
                }

                is LectureTestState.Empty -> {
                    Text("Пусто")
                }

                is LectureTestState.Error -> {
                    val error = (screenState as LectureTestState.Error).e
                    Text("Error: ${error.message}")
                }
            }
        }
    }

    @Composable
    fun NotFullAnswerDialog(
        isDialogOnNotFullOpen: MutableState<Boolean>
    ) {
        if (isDialogOnNotFullOpen.value) {
            AlertDialog1(
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
        isDialogOnConfirmOpen: MutableState<Boolean>
    ) {
        if (isDialogOnConfirmOpen.value) {
            AlertDialog1(
                onDismissRequest = {},
                title = { Text("Проверить результаты?") },
                text = { Text("Вы уверены, что хотите отправить тест на проверку?") },
                confirmButton = {
                    TextButton(onClick = {}) {
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