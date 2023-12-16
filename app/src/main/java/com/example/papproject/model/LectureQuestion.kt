package com.example.papproject.model

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.papproject.ui.theme.PAPProjectTheme
import com.example.papproject.ui.theme.montserratFontFamily

open class LectureQuestion(
    private val question: String,
    private val correctAnswer: String,
    private val answers: List<String>
) {
    var isAnsweredCorrectly: Boolean = false

    @Composable
    open fun QuestionElement() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DisplayQuestionText(
                Modifier
                    .weight(0.7f)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.weight(0.05f))
            AnswerVariants(Modifier.weight(0.25f))
        }
    }

    @Composable
    open fun DisplayQuestionText(modifier: Modifier = Modifier) {
        Card(
            modifier = modifier.fillMaxSize(),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = question,
                    style = MaterialTheme.typography.headlineLarge,
                    fontFamily = montserratFontFamily,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
    @Composable
    fun AnswerVariants(modifier: Modifier = Modifier) {
        var selectedAnswer by remember { mutableStateOf("") }
        var isCorrect by remember { mutableStateOf(false) }

        Column(modifier = modifier) {
            answers.forEach { answer ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (answer == selectedAnswer),
                            onClick = {
                                selectedAnswer = answer
                                isCorrect = (answer == correctAnswer)
                                isAnsweredCorrectly = isCorrect
                            },
                            role = Role.RadioButton
                        )
                        .padding(8.dp)
                ) {
                    RadioButton(
                        selected = (answer == selectedAnswer),
                        onClick = null
                    )
                    Text(
                        text = answer,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }

}