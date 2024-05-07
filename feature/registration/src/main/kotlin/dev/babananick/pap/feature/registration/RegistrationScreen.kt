package dev.babananick.pap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.babananick.pap.ui.components.EmailField
import dev.babananick.pap.ui.components.LinkToLogin
import dev.babananick.pap.ui.components.PasswordField
import dev.babananick.pap.ui.theme.montserratFontFamily

@Composable
fun RegistrationScreen(
    onLoginLinkClick: () -> Unit
) {
    val auth = Firebase.auth
    val emailText = remember { mutableStateOf("") }
    val passwordText = remember { mutableStateOf("") }
    val repeatPasswordText = remember { mutableStateOf("") }
    val message = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Регистрация в Psychology at Pocket",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = montserratFontFamily
        )
        Spacer(modifier = Modifier.height(16.dp))
        EmailField(emailText, message)
        Spacer(modifier = Modifier.height(16.dp))
        PasswordField(passwordText, "Введите пароль", message)
        Spacer(modifier = Modifier.height(16.dp))
        PasswordField(repeatPasswordText, "Повторите пароль", message)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            message.value,
            color = Color.Red,
            fontFamily = montserratFontFamily
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (passwordText.value != repeatPasswordText.value) {
                    message.value = "Пароли не совпадают!"
                } else if (passwordText.value.length < 6) {
                    message.value = "Пароль должен быть не менее 6 знаков!"
                } else {
                    auth.createUserWithEmailAndPassword(emailText.value, passwordText.value)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                message.value = "Вы успешно зарегистрировались!"
                                auth.currentUser
                            } else {
                                message.value = "Почта занята!"
                            }
                        }
                }
            }
        ) {
            Text(
                text = "Зарегистрироваться",
                style = MaterialTheme.typography.labelLarge
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LinkToLogin(
            onClick = onLoginLinkClick
        )
    }
}
