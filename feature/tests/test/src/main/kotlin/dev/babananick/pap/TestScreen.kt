package dev.babananick.pap

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.babananick.pap.buttons.NavigateBorder
import dev.babananick.pap.buttons.NavigateFilled
import dev.babananick.pap.components.InnerNavigation
import dev.babananick.pap.snackbar.TopSnackbar

data class TestScreen(
    val testName: String,
) : Screen {

    @Composable
    override fun Content() {
        val testVM = hiltViewModel<TestScreenViewModel,
                TestScreenViewModel.TestScreenViewModelFactory> { factory ->
            factory.create(
                testName = testName
            )
        }

        var showSnackbar by remember { mutableStateOf(false) }
        val state by testVM.state.collectAsState()
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state) {
                is TestState.ShowTest -> {
                    val data = (state as TestState.ShowTest).data
                    Navigator(
                        QuestionScreen(data, data.questions!!.first()),
                        onBackPressed = {
                            true.also {
                                testVM.popScreen()
                                testVM.fetcher(testVM.peekScreen()) {}
                            }
                        }
                    ) {
                        TopSnackbar(
                            message = "Вы пропустили вопросы!",
                            show = showSnackbar,
                            onDismiss = {
                                showSnackbar = false
                            }
                        )
                        val navigator = LocalNavigator.currentOrThrow
                        val isPreviousEnabled by testVM.isNotInBegging.collectAsState()
                        val isNextEnabled by testVM.isNotInEnd.collectAsState()
                        val previous by testVM.previousQuestionPosition.collectAsState()
                        val next by testVM.nextQuestionPosition.collectAsState()
                        val currentPosition by testVM.currentQuestionPosition.collectAsState()
                        InnerNavigation(
                            currentQuestion = currentPosition,
                            test = data,
                            navigator = navigator,
                        ) { questionPos ->
                            testVM.fetcher(questionPos) {
                                testVM.pushScreen(questionPos)
                            }
                        }
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            item {
                                CurrentScreen()
                            }
                            item {
                                Row {
                                    if (isPreviousEnabled) {
                                        NavigateBorder(
                                            modifier = Modifier.padding(
                                                start = 20.dp,
                                                end = 10.dp
                                            ),
                                            onClick = {
                                                navigator.push(
                                                    QuestionScreen(
                                                        test = data,
                                                        question = data.questions!![previous]
                                                    )
                                                )
                                                testVM.fetcher(previous) {
                                                    testVM.pushScreen(previous)
                                                }
                                            },
                                            text = "Предыдущий"
                                        )
                                    }

                                    if (isNextEnabled) {
                                        NavigateFilled(
                                            modifier = Modifier.padding(
                                                start = 10.dp,
                                                end = 20.dp
                                            ),
                                            onClick = {
                                                navigator.push(
                                                    QuestionScreen(
                                                        test = data,
                                                        question = data.questions!![next]
                                                    )
                                                )
                                                testVM.fetcher(next) {
                                                    testVM.pushScreen(next)
                                                }
                                            },
                                            text = "Следующий"
                                        )
                                    } else {
                                        NavigateFilled(
                                            modifier = Modifier.padding(
                                                start = 10.dp,
                                                end = 20.dp
                                            ),
                                            onClick = {
                                                showSnackbar = !testVM.proceedTest(data)
                                            },
                                            text = "Закончить"
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                is TestState.ShowResults -> {
                    val results = (state as TestState.ShowResults).analyzer
                    val interpretation = results.prepareInterpretation()
                    interpretation.forEach {
                        Text(it.message)
                        Text(it.result)
                    }
                }

                is TestState.Base -> {
                    BaseScreenStateValues((state as TestState.Base).states)
                }
            }
        }
    }

}