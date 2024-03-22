@file:OptIn(ExperimentalMaterial3Api::class)

package dev.babananick.pap

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import compose.icons.TablerIcons
import compose.icons.tablericons.Eye
import compose.icons.tablericons.EyeOff
import dev.babananick.pap.ui.theme.Green50
import dev.babananick.pap.theme.montserratFontFamily

@Composable
fun DefaultText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Justify,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.displaySmall,
        fontWeight = FontWeight.Bold,
        fontFamily = montserratFontFamily,
        modifier = modifier,
        textAlign = textAlign
    )
}

@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Green50,
            contentColor = Color.White
        ),
        border = BorderStroke(1.dp, Green50),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
    ) {
        Text("Результаты")
    }
}

@Composable
fun LinkToRegistration(
    onClick: () -> Unit
) {
    Row {
        Text("Нет аккаунта? ", fontFamily = montserratFontFamily)
        Text(
            "Регистрация",
            modifier = Modifier.clickable(onClick = onClick),
            color = Color.Blue,
            fontFamily = montserratFontFamily
        )
    }
}

@Composable
fun LinkToLogin(
    onClick: () -> Unit
) {
    Row {
        Text("Есть аккаунт? ", fontFamily = montserratFontFamily)
        Text(
            "Вход",
            modifier = Modifier.clickable(onClick = onClick),
            color = Color.Blue,
            fontFamily = montserratFontFamily
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
        label = { Text(prompt, fontFamily = montserratFontFamily) },
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
        textStyle = TextStyle(fontFamily = montserratFontFamily),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
            containerColor = Color.White
        )
    )
}

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
        label = { Text("Email", fontFamily = montserratFontFamily) },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(fontFamily = montserratFontFamily),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
            containerColor = Color.White
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
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = Color.White
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
    ) {
        Text(prompt, fontFamily = montserratFontFamily)
    }
}


@Composable
fun LectureModule.LectureElement(
    modifier: Modifier = Modifier,
    navigator: Navigator,
) {
    var currentLecture by remember { mutableStateOf("") }
    val isShown = remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxWidth(0.875f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            DefaultText(moduleName)
        }
        submodulesNames.forEach { lecture ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {

                    }
            ) {
                DefaultText(lecture)
            }
        }
    }


}

