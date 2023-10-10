package com.example.papproject.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class HomeScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(Modifier.fillMaxSize()) {
            Text("WELCOME TO PAP!!!!!!",
                style = MaterialTheme.typography.displayLarge)
            Text("Do you want leave it?",
                style = MaterialTheme.typography.bodySmall)
            Button(
                onClick = {
                    navigator.replace(RegistrationScreen(navigator))
                }
            ){
                Text("YES")
            }

        }

    }
}