package dev.babananick.pap.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import cafe.adriel.voyager.navigator.Navigator
import dev.babananick.pap.screens.LoginScreen
import dev.babananick.pap.screens.RegistrationScreen
import dev.babananick.pap.ui.theme.Green50
import dev.babananick.pap.ui.theme.montserratFontFamily
import compose.icons.TablerIcons
import compose.icons.tablericons.Eye
import compose.icons.tablericons.EyeOff
import dev.babananick.pap.LectureModule
import dev.babananick.pap.screens.LectureScreen
import dev.babananick.pap.vm.HomeScreenViewModel

@Composable
fun DefaultText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Justify,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h6,
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
            backgroundColor = Green50,
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
fun LinkToRegistration(navigator: Navigator) {
    Row {
        Text("Нет аккаунта? ", fontFamily = montserratFontFamily)
        Text(
            "Регистрация",
            modifier = Modifier.clickable {
                navigator.replace(RegistrationScreen())
            },
            color = Color.Blue,
            fontFamily = montserratFontFamily
        )
    }
}

@Composable
fun LinkToLogin(navigator: Navigator) {
    Row {
        Text("Есть аккаунт? ", fontFamily = montserratFontFamily)
        Text(
            "Вход",
            modifier = Modifier.clickable {
                navigator.replace(LoginScreen())
            },
            color = Color.Blue,
            fontFamily = montserratFontFamily
        )
    }
}

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
            focusedBorderColor = MaterialTheme.colors.secondary,
            unfocusedBorderColor = MaterialTheme.colors.secondary,
            backgroundColor = Color.White
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
            focusedBorderColor = MaterialTheme.colors.secondary,
            unfocusedBorderColor = MaterialTheme.colors.secondary,
            backgroundColor = Color.White
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
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = Color.White
        ),
        border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
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
    viewModel: HomeScreenViewModel,
) {
    var currentLecture by remember { mutableStateOf("") }
    val isShown = remember { mutableStateOf(false) }
    AlertOnConfirmRewrite(isShown, navigator,this.moduleName, currentLecture)
    Column(
        modifier = modifier.fillMaxWidth(0.875f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.secondary)
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
                        if (viewModel.isResultsExist(moduleName, lecture)) {
                            currentLecture = lecture
                            isShown.value = true
                        } else {
                            navigator.push(LectureScreen(moduleName, lecture))
                        }
                    }
            ) {
//                    if(results[moduleName]?.containsKey(lecture) == true ){
//                        Icon(Icons.Default.Check, "Passed")
//                    }
                DefaultText(lecture)
            }
        }
    }


}

@Composable
fun AlertOnConfirmRewrite(
    isShown: MutableState<Boolean>,
    navigator: Navigator,
    moduleName: String,
    lecture: String,
) {
    if (isShown.value) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { isShown.value = false },
            title = { androidx.compose.material3.Text("Перепройти тест?") },
            text = { androidx.compose.material3.Text("Вы уверены, что хотите перепройти тест? Вы ранее его проходили") },
            confirmButton = {
                androidx.compose.material3.TextButton(onClick = {
                    navigator.push(LectureScreen(moduleName, lecture))
                    isShown.value = false
                }) {
                    androidx.compose.material3.Text("Да")
                }
            },
            dismissButton = {
                androidx.compose.material3.TextButton(onClick = { isShown.value = false }) {
                    androidx.compose.material3.Text("Отмена")
                }
            }
        )
    }
}