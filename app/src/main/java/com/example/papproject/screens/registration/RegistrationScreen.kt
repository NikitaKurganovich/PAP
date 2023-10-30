package com.example.papproject.screens.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.example.papproject.screens.HomeScreen

data class RegistrationScreen(val navigator: Navigator): Screen {
    @Composable
    override fun Content() {
        Column(Modifier
            .fillMaxSize()
            ){
            Text("Nah, you can't",
                style = MaterialTheme.typography.displayLarge)
            Button(
                onClick = {
                    navigator.replace(HomeScreen())
                }
            ){
                Text("Damn")
            }
        }

    }
}