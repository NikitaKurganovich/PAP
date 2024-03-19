package dev.babananick.pap.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.babananick.pap.model.LectureModule
import dev.babananick.pap.util.DefaultText
import dev.babananick.pap.util.LectureElement
import dev.babananick.pap.vm.HomeScreenViewModel
import dev.babananick.pap.vm.HomeState

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val homeVM: HomeScreenViewModel = hiltViewModel()
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
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            DefaultText("Продолжите обучение!")
                            Spacer(Modifier.height(10.dp))
                        }
                        items(data) {
                            it.LectureElement(
                                navigator = navigator,
                                viewModel = homeVM
                            )
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

