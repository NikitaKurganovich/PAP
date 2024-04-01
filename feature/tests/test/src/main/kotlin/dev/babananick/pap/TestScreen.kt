package dev.babananick.pap

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import javax.inject.Inject

data class TestScreen(val testName: String) : Screen {
    @Inject lateinit var testViewModelAssistedFactory: TestScreenViewModel.Factory

    @Composable
    override fun Content() {
        val testVM: TestScreenViewModel = viewModel(
            factory =  TestScreenViewModel.provideFactory(testViewModelAssistedFactory, testName)
        )

        val state by testVM.state.collectAsState()
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state) {
                is TestState.ShowTest ->{
                    val data = (state as TestState.ShowTest).data

                }
                is TestState.ShowResults ->{
                    val results = (state as TestState.ShowResults).interpretation
                }
                is TestState.Base -> {
                    BaseScreenStateValues((state as TestState.Base).states)
                }
            }
        }
    }

}