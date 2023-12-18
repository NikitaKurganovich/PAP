package com.example.papproject.util

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.example.papproject.screens.LoginScreen
import com.example.papproject.screens.RegistrationScreen
import com.example.papproject.ui.theme.Green50
import com.example.papproject.ui.theme.montserratFontFamily
import compose.icons.TablerIcons
import compose.icons.tablericons.Eye
import compose.icons.tablericons.EyeOff

@Composable
fun DefaultText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h6,
        fontWeight = FontWeight.Bold,
        fontFamily = montserratFontFamily,
        modifier = modifier
    )
}

@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Green50
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
        Text("Don't have an account? ", fontFamily = montserratFontFamily)
        Text(
            "Sign up",
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
        Text("Already have account? ", fontFamily = montserratFontFamily)
        Text(
            "Login",
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
    message: MutableState<String>
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
    message: MutableState<String>
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
fun CustomButton(prompt: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = MaterialTheme.colors.secondary
        ),
        border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
    ) {
        Text(prompt, fontFamily = montserratFontFamily)
    }
}
@Composable
fun Modifier.simpleVerticalScrollbar(
    state: LazyListState,
    width: Dp = 8.dp
): Modifier {
    val targetAlpha = if (state.isScrollInProgress) 1f else 0f
    val duration = if (state.isScrollInProgress) 150 else 500

    val alpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(durationMillis = duration)
    )

    return drawWithContent {
        drawContent()

        val firstVisibleElementIndex = state.layoutInfo.visibleItemsInfo.firstOrNull()?.index
        val needDrawScrollbar = state.isScrollInProgress || alpha > 0.0f

        // Draw scrollbar if scrolling or if the animation is still running and lazy column has content
        if (needDrawScrollbar && firstVisibleElementIndex != null) {
            val elementHeight = this.size.height / state.layoutInfo.totalItemsCount
            val scrollbarOffsetY = firstVisibleElementIndex * elementHeight
            val scrollbarHeight = state.layoutInfo.visibleItemsInfo.size * elementHeight

            drawRect(
                color = Color.Red,
                topLeft = Offset(this.size.width - width.toPx(), scrollbarOffsetY),
                size = Size(width.toPx(), scrollbarHeight),
                alpha = alpha
            )
        }
    }
}
