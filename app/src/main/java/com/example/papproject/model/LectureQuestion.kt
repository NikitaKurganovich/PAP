package com.example.papproject.model

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.papproject.ui.theme.montserratFontFamily
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
open class LectureQuestion(
    val question: String = "",
    val correct_answer: String = "",
    val available_answers: List<String> = listOf()
) {
    var isAnsweredCorrectly: Boolean = false

    @Composable
    open fun QuestionElement(modifier: Modifier = Modifier) {
        Card(
            modifier = modifier.fillMaxSize().clickable { println(available_answers + correct_answer) },
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = question,
                    style = MaterialTheme.typography.headlineLarge,
                    fontFamily = montserratFontFamily,
                    textAlign = TextAlign.Center
                )
            }
            AnswerVariants()
        }
    }

    @Composable
    fun AnswerVariants(modifier: Modifier = Modifier) {
        var selectedAnswer by remember { mutableStateOf("") }
        var isCorrect by remember { mutableStateOf(false) }

        Column(modifier = modifier) {
            available_answers.forEach { answer ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (answer == selectedAnswer),
                            onClick = {
                                selectedAnswer = answer
                                isCorrect = (answer == correct_answer)
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