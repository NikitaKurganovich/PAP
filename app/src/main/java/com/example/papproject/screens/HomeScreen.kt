package com.example.papproject.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import com.example.papproject.vm.HomeScreenViewModel
import com.example.papproject.vm.HomeState

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val homeVM = HomeScreenViewModel()
        val screenState by homeVM.state.collectAsState()

        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (screenState) {
                is HomeState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }

                is HomeState.ShowingModules -> {

                    val data = (screenState as HomeState.ShowingModules).data
                    LazyColumn {
                        items(data) {
                            it.LectureElement(navigator = navigator)
                        }
                    }
                }

                is HomeState.Empty -> {
                    Text("Пусто")
                }

                is HomeState.Error -> {
                    val error = (screenState as HomeState.Error).e
                    Text("Error: ${error.message}")
                }
            }
        }
    }
}