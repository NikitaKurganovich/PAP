package com.example.papproject.model

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.papproject.util.DefaultText
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
open class LectureQuestion(
    val question: String = "",
    val correct_answer: String = "",
    val available_answers: List<String> = listOf()
) {

    var isAnsweredCorrectly: Boolean = false
    var isAnswered: Boolean = false

    @Composable
    open fun QuestionElement(modifier: Modifier = Modifier) {
        Card(
            modifier = modifier.fillMaxSize(0.88f),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary) // Add this line to change the card's background color
        ) {
            Column(modifier.padding(16.dp)) { // Add padding inside the card
                DefaultText(question, modifier.fillMaxWidth())
                AnswerVariants(modifier)
            }
        }
    }

    @Composable
    fun AnswerVariants(modifier: Modifier = Modifier) {
        var selectedAnswer by remember { mutableStateOf("") }

        Column(modifier = modifier) {
            available_answers.forEach { answer ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .selectable(
                            selected = (answer == selectedAnswer),
                            onClick = {
                                selectedAnswer = answer
                                isAnswered = true
                                isAnsweredCorrectly = (answer == correct_answer)
                            },
                            role = Role.RadioButton
                        )
                ) {
                    RadioButton(
                        selected = (answer == selectedAnswer),
                        onClick = null
                    )
                    DefaultText(
                        text = answer
                    )
                }
            }
        }
    }
}