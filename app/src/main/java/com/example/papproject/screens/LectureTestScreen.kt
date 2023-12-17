package com.example.papproject.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.papproject.util.CustomButton
import com.example.papproject.vm.*

data class LectureTestScreen(
    private val moduleName: String,
    private val submoduleName: String
) : Screen {

    @Composable
    override fun Content() {
        val currentVM: LectureScreenViewModel =  viewModel(factory = LectureScreenViewModelFactory(moduleName,submoduleName))
        val screenState by currentVM.state.collectAsState()
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
                        item{
                            CustomButton(onClick = {
                                var score = 0
                                data.forEach{
                                    if (it.isAnsweredCorrectly){
                                        score++
                                    }
                                    if (!it.isAnswered){
                                        println("You do not answer to all questions")
                                    } else{
                                        println(score)
                                    }
                                }
                            },
                                modifier = Modifier.fillParentMaxWidth(0.76f))
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
}