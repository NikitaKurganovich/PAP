package dev.babananick.pap

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import dev.babananick.pap.questions.Question
import dev.babananick.pap.questions.QuestionWithScale
import dev.babananick.pap.questions.QuestionWithVariants

data class QuestionScreen(
    val question: Question,
    val answerVariant: AnswerVariantWithScore? = null
): Screen {
    @Composable
    override fun Content() {
        when(question){
            is QuestionWithVariants ->{
                Column {
                    Text(question.question!!)
                    question.answer_variants!!.forEach { questionText ->
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = questionText.answer!!
                        )
                    }
                }

            }
            is QuestionWithScale ->{
                Text("WIP")
            }
        }
    }

}