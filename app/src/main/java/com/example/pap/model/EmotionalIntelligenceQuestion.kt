package com.example.pap.model

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.pap.util.DefaultText
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class EmotionalIntelligenceQuestion(
    val question: String = "",
    val related_scale: String = "",
    val answer_variants: HashMap<String, Int> = hashMapOf()
) {
    var isAnswered: Boolean = false
    var answeredScore = 0
    @Composable
    fun QuestionElement(modifier: Modifier = Modifier) {
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
        var selectedAnswer by rememberSaveable { mutableStateOf("") }

        Column(modifier = modifier) {
            answer_variants.forEach { answer ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .selectable(
                            selected = (answer.key == selectedAnswer),
                            onClick = {
                                selectedAnswer = answer.key
                                answeredScore = answer.value
                                isAnswered = true
                            },
                            role = Role.RadioButton
                        )
                ) {
                    RadioButton(
                        selected = (answer.key == selectedAnswer),
                        onClick = null
                    )
                    DefaultText(
                        text = answer.key
                    )
                }
            }
        }
    }
}