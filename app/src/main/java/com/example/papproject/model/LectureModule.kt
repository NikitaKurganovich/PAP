package com.example.papproject.model

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class LectureModule(
    val moduleName: String = "",
    val submodulesNames: List<String> = listOf()
) {
    @Composable
    fun LectureElement(modifier: Modifier = Modifier) {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = modifier.padding(5.dp)
        ) {
            Text(moduleName, style = MaterialTheme.typography.h2)


            submodulesNames.forEach {
                Text(it, Modifier.clickable {  })
            }


        }
    }
}