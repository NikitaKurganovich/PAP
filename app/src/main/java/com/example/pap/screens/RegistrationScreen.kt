package com.example.pap.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.pap.ui.theme.montserratFontFamily
import com.example.pap.util.CustomButton
import com.example.pap.util.EmailField
import com.example.pap.util.LinkToLogin
import com.example.pap.util.PasswordField
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistrationScreen() : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val db = Firebase.firestore
        val auth = Firebase.auth
        val emailText = remember { mutableStateOf("") }
        val passwordText = remember { mutableStateOf("") }
        val repeatPasswordText = remember { mutableStateOf("") }
        val message = remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Регистрация в Psychology at Pocket",
                style = MaterialTheme.typography.h5,
                color = Color.Black,
                fontFamily = montserratFontFamily
            )
            Spacer(modifier = Modifier.height(16.dp))
            EmailField(emailText, message)
            Spacer(modifier = Modifier.height(16.dp))
            PasswordField(passwordText, "Введите пароль", message)
            Spacer(modifier = Modifier.height(16.dp))
            PasswordField(repeatPasswordText, "Повторите пароль", message)
            Spacer(modifier = Modifier.height(16.dp))
            androidx.compose.material.Text(message.value, color = Color.Red, fontFamily = montserratFontFamily)
            CustomButton("Зарегистрироваться",
                {
                    if (passwordText.value != repeatPasswordText.value) {
                        message.value = "Пароли не совпадают!"
                    } else if (passwordText.value.length < 6) {
                        message.value = "Пароль должен быть не менее 6 знаков!"
                    } else {
                        auth.createUserWithEmailAndPassword(emailText.value, passwordText.value)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    message.value = "Вы успешно зарегистрировались!"
                                    db.collection("users").document(auth.currentUser!!.uid).set(emptyMap<String,Int>())
                                    auth.currentUser
                                } else {
                                    message.value = "Почта занята!"
                                }
                            }
                    }
                },
                Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            LinkToLogin(navigator)
        }

    }
}