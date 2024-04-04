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

data class TestScreen(
    val testName: String
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
                    Text("data")
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