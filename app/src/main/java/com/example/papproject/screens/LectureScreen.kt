package com.example.papproject.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.papproject.ui.theme.montserratFontFamily
import com.example.papproject.util.CustomButton
import com.example.papproject.util.simpleVerticalScrollbar
import com.example.papproject.vm.LectureScreenViewModel
import com.example.papproject.vm.LectureScreenViewModelFactory
import com.example.papproject.vm.LectureTestState
import com.example.papproject.vm.LectureTheoryState

data class LectureScreen(
    private val moduleName: String,
    private val submoduleName: String
) : Screen {
    @Composable
    override fun Content() {
        val currentVM: LectureScreenViewModel =
            viewModel(factory = LectureScreenViewModelFactory(moduleName, submoduleName))
        val screenState by currentVM.theoryState.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
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

                is LectureTheoryState.ShowingTheory -> {
                    val data = (screenState as LectureTheoryState.ShowingTheory).data

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
                                    navigator.push(LectureTestScreen(currentVM))
                                },
                                modifier = Modifier.height(50.dp)
                            )
                        }
                    }
                }

                is LectureTheoryState.Empty -> {
                    Text("Пусто")
                }

                is LectureTheoryState.Error -> {
                    val error = (screenState as LectureTheoryState.Error).e
                    Text("Error: ${error.message}")
                }
            }
        }
    }
}