package dev.babananick.pap.feature.registration

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.babananick.pap.ui.components.EmailField
import dev.babananick.pap.ui.components.LinkToLogin
import dev.babananick.pap.ui.components.PasswordField
import dev.babananick.pap.ui.theme.ralewayFontFamily

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
            text = stringResource(R.string.registration_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontFamily = ralewayFontFamily
        )
        Spacer(modifier = Modifier.height(16.dp))
        EmailField(emailText, message)
        Spacer(modifier = Modifier.height(16.dp))
        PasswordField(passwordText, stringResource(R.string.registration_password_prompt), message)
        Spacer(modifier = Modifier.height(16.dp))
        PasswordField(
            repeatPasswordText,
            stringResource(R.string.registration_repeat_password_prompt),
            message
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            message.value,
            color = MaterialTheme.colorScheme.error,
            fontFamily = ralewayFontFamily
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (passwordText.value != repeatPasswordText.value) {
                    message.value = R.string.registration_message_passwords_not_match.toString()
                } else if (passwordText.value.length < 6) {
                    message.value = R.string.registration_message_short_password.toString()
                } else {
                    auth.createUserWithEmailAndPassword(emailText.value, passwordText.value)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                message.value = R.string.registration_success_message.toString()
                                auth.currentUser
                            } else {
                                message.value = R.string.registration_message_email_busy.toString()
                            }
                        }
                }
            }
        ) {
            Text(
                text = stringResource(R.string.registration_button_text),
                style = MaterialTheme.typography.labelLarge,
                fontFamily = ralewayFontFamily
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LinkToLogin(
            onClick = onLoginLinkClick
        )
    }
}
