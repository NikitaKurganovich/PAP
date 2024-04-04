package dev.babananick.pap

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
                    var currentQuestion by remember { mutableStateOf(data.questions!![testVM.currentQuestionPosition] ) }
                    Navigator(QuestionScreen(currentQuestion)) {
                        val navigator = LocalNavigator.currentOrThrow
                        CurrentScreen()
                        Row {
                            Button(onClick = {
                                navigator.pop()
                                testVM.updateQuestionPosition()
                            }){
                                Text("Previews")
                            }
                            Button(onClick = {
                                navigator.push(QuestionScreen(testVM.toNextQuestion()))
                            }){
                                Text("Next")
                            }
                        }
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