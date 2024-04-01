package dev.babananick.pap

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

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
                            )
                        }
                    }
                }

                is HomeState.Base -> {
                    BaseScreenStateValues((screenState as HomeState.Base).state)
                }
            }
        }
    }

}

