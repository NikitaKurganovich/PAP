package com.example.papproject.model

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.example.papproject.screens.LectureScreen
import com.example.papproject.util.DefaultText
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class LectureModule(
    val moduleName: String = "",
    val submodulesNames: List<String> = listOf()
) {
    @Composable
    fun LectureElement(
        modifier: Modifier = Modifier,
        navigator: Navigator
    ) {

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
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            navigator.push(LectureScreen(moduleName, lecture))
                        }
                ) {
                    DefaultText(lecture)
                }
            }
        }

    }
}