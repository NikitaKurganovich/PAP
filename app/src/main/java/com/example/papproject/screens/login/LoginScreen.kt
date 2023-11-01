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
import com.example.papproject.util.Hash
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import java.lang.Exception

class LoginScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val db = Firebase.firestore
        val auth = Firebase.auth
        var loginText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }
        var message by remember { mutableStateOf("") }

        Column {
            TextField(
                value = loginText,
                onValueChange = {
                    loginText = it
                    message = ""
                },
                placeholder = { Text(text = "Введите логин") }
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
                    db.collection("Users")
                        .get()
                        .addOnSuccessListener { result ->
                            for (document in result) {
                                if (document.id == loginText &&
                                    Hash.getSHA(passwordText) == document.data["password"] as String
                                ) {
                                    auth.signInAnonymously()
                                    message = "Вы успешно вошли!\nИдёт загрузка"
                                } else {
                                    message = "Неправильный логин или пароль"
                                }
                            }
                        }
                        .addOnFailureListener {
                            println(it.message)
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