package dev.babananick.pap

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

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

        val state by testVM.state.collectAsState()
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state) {
                is TestState.ShowTest -> {
                    val data = (state as TestState.ShowTest).data
                    Navigator(
                        QuestionScreen(data, 0),
                        onBackPressed = {
                            true.also {
                                testVM.popScreen()
                                testVM.fetcher(testVM.peekScreen()){}
                            }
                        }
                    ) {
                        val navigator = LocalNavigator.currentOrThrow
                        val isPreviousEnabled by testVM.isNotInBegging.collectAsState()
                        val isNextEnabled by testVM.isNotInEnd.collectAsState()
                        val previous by testVM.previousQuestionPosition.collectAsState()
                        val next by testVM.nextQuestionPosition.collectAsState()
                        val currentPosition by testVM.currentQuestionPosition.collectAsState()
                        InnerNavigation(
                            currentPosition,
                            data,
                            navigator,
                        ) { int ->
                            testVM.fetcher(int) {
                                testVM.pushScreen(int)
                            }
                        }
                        CurrentScreen()
                        NextAndPrevious(
                            onNext = {
                                navigator.push(
                                    QuestionScreen(
                                        test = data,
                                        questionPosition = next
                                    )
                                )
                                testVM.fetcher(next) {
                                    testVM.pushScreen(next)
                                }
                            },
                            onPrevious = {
                                navigator.push(
                                    QuestionScreen(
                                        test = data,
                                        questionPosition = previous
                                    )
                                )
                                testVM.fetcher(previous) {
                                    testVM.pushScreen(previous)
                                }
                            },
                            isPreviousEnabled = isPreviousEnabled,
                            isNextEnabled = isNextEnabled,
                        )
                    }
                }

                is TestState.ShowResults -> {
                    val results = (state as TestState.ShowResults).interpretation
                    Text("results")
                }

                is TestState.Base -> {
                    BaseScreenStateValues((state as TestState.Base).states)
                }
            }
        }
    }

}