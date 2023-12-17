package com.example.papproject.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.example.papproject.ui.theme.montserratFontFamily
import com.example.papproject.util.CustomButton
import com.example.papproject.util.EmailField
import com.example.papproject.util.LinkToRegistration
import com.example.papproject.util.PasswordField
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val db = Firebase.firestore
        val auth = Firebase.auth
        val emailText = remember { mutableStateOf("") }
        val passwordText = remember { mutableStateOf("") }
        val message = remember { mutableStateOf("") }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Вход в PAP",
                style = MaterialTheme.typography.h5,
                color = Color.Black,
                fontFamily = montserratFontFamily
            )
            Spacer(modifier = Modifier.height(16.dp))
            EmailField(emailText, message)
            Spacer(modifier = Modifier.height(16.dp))
            PasswordField(passwordText, "Введите пароль", message)
            Text(message.value, color = Color.Red, fontFamily = montserratFontFamily)
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(
                "Войти",
                {
                    if (emailText.value.isNotEmpty() || passwordText.value.isNotEmpty()){
                        auth.signInWithEmailAndPassword(emailText.value, passwordText.value)
                            .addOnCompleteListener() { task ->
                                if (task.isSuccessful) {
                                    message.value = "Вы успешно зашли!"
                                    auth.currentUser
                                } else {
                                    message.value = "Некорректная почта или пароль"
                                }
                            }
                    } else {
                        message.value = "Поля почты и пароля не должны быть пустыми!"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            LinkToRegistration(navigator)
        }
    }
}