package com.example.pap.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.pap.ui.theme.montserratFontFamily
import com.example.pap.util.CustomButton
import com.example.pap.util.DefaultText
import com.example.pap.vm.LectureScreenViewModel
import com.example.pap.vm.LectureScreenViewModelFactory
import com.example.pap.vm.LectureState
import kotlinx.coroutines.flow.update

data class LectureScreen(
    private val moduleName: String,
    private val submoduleName: String,
) : Screen {
    @Composable
    override fun Content() {
        val currentVM: LectureScreenViewModel =
            viewModel(factory = LectureScreenViewModelFactory(moduleName, submoduleName))
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
                is LectureState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }

                is LectureState.ShowingQuestions -> {
                    val data = (screenState as LectureState.ShowingQuestions).data
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
                                    currentVM.collectResults(data, isDialogOnNotFullOpen, isDialogOnConfirmOpen)
                                },
                                modifier = Modifier.fillParentMaxWidth(0.76f)
                            )
                        }
                    }
                }

                is LectureState.ShowingResults -> {
                    val score = (screenState as LectureState.ShowingResults).score
                    val questionNumber = (screenState as LectureState.ShowingResults).questionNumber

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        DefaultText(
                            "Ваш результат: $score из $questionNumber",
                            Modifier.fillMaxWidth(0.8f)
                        )
                        Row(
                            Modifier.fillMaxWidth(0.8f),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            TextButton(
                                onClick = {
                                    currentVM.isResultsSend.update { false }
                                    currentVM.isTheoryRead.update { false }
                                },
                                modifier = Modifier
                                    .weight(0.45f),
                                shape = RoundedCornerShape(CornerSize(10)),
                                colors = ButtonDefaults.buttonColors(Color(0XFFD0E6E1))
                            ) {
                                DefaultText("Перепройти")
                            }
                            Spacer(Modifier.weight(0.1f))
                            TextButton(
                                onClick = {
                                    navigator.pop()
                                },
                                modifier = Modifier
                                    .weight(0.45f),
                                shape = RoundedCornerShape(CornerSize(10)),
                                colors = ButtonDefaults.buttonColors(Color(0XFFD0E6E1))
                            ) {
                                DefaultText("Вернутся")
                            }
                        }
                    }


                }

                is LectureState.Empty -> {
                    Text("Пусто")
                }

                is LectureState.Error -> {
                    val error = (screenState as LectureState.Error).e
                    Text("Error: ${error.message}")
                }

                is LectureState.ShowingTheory -> {
                    val data = (screenState as LectureState.ShowingTheory).data

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(listOf(data)) { text ->
                            Text(text = text, fontFamily = montserratFontFamily, textAlign = TextAlign.Justify)
                        }
                        item {
                            Spacer(Modifier.height(5.dp))
                            CustomButton(
                                prompt = "Пройти тест по лекции",
                                onClick = {
                                    currentVM.isTheoryRead.update { true }
                                },
                                modifier = Modifier.height(50.dp)
                            )
                        }
                    }
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
                        viewModel.upsertScore(
                            viewModel.moduleName,
                            viewModel.submoduleName,
                            viewModel.score
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
}