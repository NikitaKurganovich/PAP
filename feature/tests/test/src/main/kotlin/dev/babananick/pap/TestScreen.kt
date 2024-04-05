package dev.babananick.pap

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator

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
                    TabNavigator(
                        QuestionTab(data, 0)
                    ) {
                        val isPreviousEnabled by testVM.isNotInBegging.collectAsState()
                        val isNextEnabled by testVM.isNotInEnd.collectAsState()
                        val currentQuestion by testVM.currentQuestionPosition.collectAsState()
                        InnerTabNavigation(
                            data,
                            testVM.fetchPosition()
                        )
                        CurrentTab()
                        NextAndPrevious(
                            onNext = {
                                testVM.increasePosition()
                            },
                            onPrevious = {
                                testVM.decreasePosition()
                            },
                            isPreviousEnabled = isPreviousEnabled,
                            isNextEnabled = isNextEnabled,
                            currentQuestion = currentQuestion,
                            test = data
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