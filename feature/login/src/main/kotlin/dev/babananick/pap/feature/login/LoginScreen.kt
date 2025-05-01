package dev.babananick.pap.feature.login

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.babananick.pap.ui.components.EmailField
import dev.babananick.pap.ui.components.LinkToRegistration
import dev.babananick.pap.ui.components.PasswordField
import dev.babananick.pap.ui.theme.ralewayFontFamily

@Composable
fun LoginScreen(
    onRegistrationLinkClick: () -> Unit
) {
    val auth = Firebase.auth
    val emailText = remember { mutableStateOf("") }
    val passwordText = remember { mutableStateOf("") }
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
            text = stringResource(R.string.login_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontFamily = ralewayFontFamily
        )
        Spacer(modifier = Modifier.height(16.dp))
        EmailField(emailText, message)
        Spacer(modifier = Modifier.height(16.dp))
        PasswordField(passwordText, stringResource(R.string.login_password_prompt), message)
        Text(
            message.value,
            color = MaterialTheme.colorScheme.error,
            fontFamily = ralewayFontFamily
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (emailText.value.isNotEmpty() || passwordText.value.isNotEmpty()) {
                    auth.signInWithEmailAndPassword(emailText.value, passwordText.value)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                message.value = R.string.login_success_message.toString()
                                auth.currentUser
                            } else {
                                message.value = R.string.login_incorrect_data_message.toString()
                            }
                        }
                } else {
                    message.value = R.string.login_empty_fields_message.toString()
                }
            }) {
            Text(
                text = stringResource(R.string.login_button_text),
                style = MaterialTheme.typography.labelLarge,
                fontFamily = ralewayFontFamily
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LinkToRegistration(
            onClick = onRegistrationLinkClick
        )
    }
}
