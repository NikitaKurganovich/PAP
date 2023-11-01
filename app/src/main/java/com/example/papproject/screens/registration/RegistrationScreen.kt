package com.example.papproject.screens.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.papproject.screens.HomeScreen
import com.example.papproject.screens.login.LoginScreen
import com.example.papproject.util.Hash
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistrationScreen() : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val db = Firebase.firestore
        val auth = Firebase.auth

        var loginText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }
        var repeatPasswordText by remember { mutableStateOf("") }
        var message by remember { mutableStateOf("") }

        Column(
            Modifier
                .fillMaxSize()
        ) {
            Text("Регистрация")
            TextField(
                value = loginText,
                onValueChange = {
                    loginText = it
                    message = ""
                },
                placeholder = { androidx.compose.material.Text(text = "Введите логин") }
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
                        db.collection("Users")
                            .get()
                            .addOnSuccessListener { result ->
                                for(document in result){
                                    if(document.id == loginText){
                                        message = "Логин занят! Попробуйте другой."
                                    }
                                }
                                if(message != "Логин занят! Попробуйте другой."){
                                    db.collection("Users")
                                        .document(loginText)
                                        .set(
                                            hashMapOf(
                                                "password" to Hash.getSHA(passwordText)
                                            )
                                        )
                                    auth.signInAnonymously()
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