package dev.babananick.pap.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import compose.icons.TablerIcons
import compose.icons.tablericons.Eye
import compose.icons.tablericons.EyeOff
import dev.babananick.pap.ui.theme.ralewayFontFamily


@Composable
fun LinkToRegistration(
    onClick: () -> Unit
) {
    Row {
        Text(
            text = stringResource(R.string.link_to_registration_text),
            fontFamily = ralewayFontFamily,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = stringResource(R.string.link_to_registration_link),
            modifier = Modifier.clickable(onClick = onClick),
            color = MaterialTheme.colorScheme.primary,
            fontFamily = ralewayFontFamily
        )
    }
}

@Composable
fun LinkToLogin(
    onClick: () -> Unit
) {
    Row {
        Text(
            text = stringResource(R.string.link_to_login_text),
            fontFamily = ralewayFontFamily,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = stringResource(R.string.link_to_login_link),
            modifier = Modifier.clickable(onClick = onClick),
            color = MaterialTheme.colorScheme.primary,
            fontFamily = ralewayFontFamily
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    password: MutableState<String>,
    prompt: String,
    message: MutableState<String>,
) {
    var isVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = password.value,
        onValueChange = {
            password.value = it.replace(" ", "")
            message.value = ""
        },
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        label = { Text(prompt, fontFamily = ralewayFontFamily) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = { isVisible = !isVisible }) {
                Icon(
                    imageVector = if (isVisible) TablerIcons.Eye else TablerIcons.EyeOff,
                    contentDescription = if (isVisible) "Hide password" else "Show password"
                )
            }
        },
        textStyle = TextStyle(fontFamily = ralewayFontFamily),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(
    email: MutableState<String>,
    message: MutableState<String>,
) {
    OutlinedTextField(
        value = email.value,
        onValueChange = {
            email.value = it.replace(" ", "")
            message.value = ""
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        label = { Text("Email", fontFamily = ralewayFontFamily) },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(fontFamily = ralewayFontFamily),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}

@Composable
fun CustomButton(
    prompt: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
    ) {
        Text(prompt, fontFamily = ralewayFontFamily)
    }
}