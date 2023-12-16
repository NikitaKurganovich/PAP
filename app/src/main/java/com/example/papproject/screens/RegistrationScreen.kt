package com.example.papproject.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistrationScreen() : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val db = Firebase.firestore
        val auth = Firebase.auth
        var emailText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }
        var repeatPasswordText by remember { mutableStateOf("") }
        var message by remember { mutableStateOf("") }

        Column(
            Modifier
                .fillMaxSize()
        ) {
            Text("Регистрация")
            TextField(
                value = emailText,
                onValueChange = {
                    emailText = it
                    message = ""
                },
                placeholder = { androidx.compose.material.Text(text = "Введите вашу почту") }
            )
            TextField(
                value = passwordText,
                onValueChange = {
                    passwordText = it
                    message = ""
                },
                placeholder = { androidx.compose.material.Text(text = "Введите пароль") }
            )
            TextField(
                value = repeatPasswordText,
                onValueChange = {
                    repeatPasswordText = it
                    message = ""
                },
                placeholder = { androidx.compose.material.Text(text = "Повторите пароль") }
            )
            Button(
                onClick = {
                    if(passwordText != repeatPasswordText){
                        message = "Пароли не совпадают!"
                    } else {
                        auth.createUserWithEmailAndPassword(emailText, passwordText)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    message = "Вы успешно зарегистрировались!"
                                    db.collection("users").document(auth.currentUser!!.uid).set(hashMapOf("results" to "bad"))
                                    auth.currentUser
                                } else {
                                    message = "Почта занята!"
                                }
                            }
                    }
                }
            ) {
                Text("Зарегистрироваться")
            }
            Text(message)
            Button(
                onClick = {
                    navigator.replace(LoginScreen())
                }
            ) {
                Text("Войти в существующий аккаунт")
            }
        }

    }
}