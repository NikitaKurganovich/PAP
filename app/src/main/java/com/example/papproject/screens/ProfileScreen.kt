package com.example.papproject.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import com.example.papproject.util.DefaultText
import com.example.papproject.vm.ProfileScreenViewModel
import com.example.papproject.vm.ProfileState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileScreen : Screen {
    @Composable
    override fun Content() {

        val profileVM: ProfileScreenViewModel = viewModel()
        val tabNavigator = LocalTabNavigator.current
        val screenState by profileVM.state.collectAsState()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (screenState) {
                is ProfileState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }

                is ProfileState.ShowingResults -> {

                    val data = (screenState as ProfileState.ShowingResults).data
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            DefaultText("Ваши результаты, ${Firebase.auth.currentUser?.email}")
                            Spacer(Modifier.height(20.dp))
                        }
                        items(data.map { it.toPair() }) { item ->
                            if (item.first != "Эмоциональный интеллект") {
                                Box(
                                    modifier = Modifier
                                        .background(MaterialTheme.colors.secondary)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    DefaultText("Модуль ${item.first}")
                                }

                                Spacer(Modifier.height(10.dp))
                                item.second.forEach { (s, i) ->
                                    DefaultText("$s - $i балл(-ов)")
                                }
                                Spacer(Modifier.height(20.dp))
                            } else{
                                Box(
                                    modifier = Modifier
                                        .background(MaterialTheme.colors.secondary)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    DefaultText(item.first)
                                }
                                Spacer(Modifier.height(10.dp))
                                item.second.forEach{ element ->
                                    DefaultText(
                                        "${element.key} - ${element.value} балл(-ов) (${
                                            if (element.value > 13) "Высокий"
                                            else if (element.value > 7) "Средний" else "Низкий"
                                        })"
                                    )
                                    Spacer(Modifier.height(20.dp))
                                }

                            }
                        }
                        item {
                            Button(
                                onClick = {
                                    Firebase.auth.signOut()
                                }
                            ) {
                                Text("Logout")
                            }
                        }
                    }
                }

                is ProfileState.Empty -> {
                    Text("Пусто")
                    Button(
                        onClick = {
                            Firebase.auth.signOut()
                        }
                    ) {
                        Text("Logout")
                    }
                }

                is ProfileState.Error -> {
                    val error = (screenState as ProfileState.Error).e
                    Text("Error: ${error.message}")
                }
            }


        }


    }
}
