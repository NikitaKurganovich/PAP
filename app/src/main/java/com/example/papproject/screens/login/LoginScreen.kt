package com.example.papproject.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.papproject.screens.registration.RegistrationScreen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val db = Firebase.firestore
        val auth = Firebase.auth
        var emailText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }
        var message by remember { mutableStateOf("") }

        Column {
            TextField(
                value = emailText,
                onValueChange = {
                    emailText = it
                    message = ""
                },
                placeholder = { Text(text = "Введите вашу почту") }
            )
            TextField(
                value = passwordText,
                onValueChange = {
                    passwordText = it
                    message = ""
                },
                placeholder = { Text(text = "Введите пароль") }
            )
            Button(
                onClick = {
                    auth.signInWithEmailAndPassword(emailText, passwordText)
                        .addOnCompleteListener() { task ->
                            if (task.isSuccessful) {
                                message = "Вы успешно зашли!"
                                auth.currentUser
                            } else {
                                message = "Некорректная почта или пароль"
                            }
                        }

                }
            ) {
                Text("Войти")
            }
            Text(message)
            Button(
                onClick = {
                    navigator.replace(RegistrationScreen())
                }
            ){
                Text("Регистрация")
            }
        }
    }
}