package dev.babananick.pap

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen

class TestChooseScreen: Screen {
    @Composable
    override fun Content() {
        val chooseViewModel: TestChooseViewModel = hiltViewModel()
        val screenState by chooseViewModel.state.collectAsState()

        when(screenState){
            is TestChooseState.ShowingTestsChoose ->{
                val data = (screenState as TestChooseState.ShowingTestsChoose).tests
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(data) { text ->
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            DefaultText(text, Modifier.clickable {

                            })
                        }
                    }
                }
            }
            is ScreenStates.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
            is ScreenStates.Empty -> {
                Text("Пусто")
            }

            is ScreenStates.Error -> {
                val error = (screenState as ScreenStates.Error).error
                Text("Error: ${error.message}")
            }
        }
    }
}