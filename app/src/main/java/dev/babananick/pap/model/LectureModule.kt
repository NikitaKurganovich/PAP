package dev.babananick.pap.model

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import dev.babananick.pap.screens.LectureScreen
import dev.babananick.pap.util.DefaultText
import dev.babananick.pap.vm.HomeScreenViewModel
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class LectureModule(
    val moduleName: String = "",
    val submodulesNames: List<String> = listOf(),
) {
    @Composable
    fun LectureElement(
        modifier: Modifier = Modifier,
        navigator: Navigator,
        viewModel: HomeScreenViewModel,
        results: HashMap<String, HashMap<String, Int>>
    ) {
        var currentLecture by remember { mutableStateOf("") }
        val isShown = remember { mutableStateOf(false) }
        AlertOnConfirmRewrite(isShown, navigator, currentLecture)
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
                    if(results[moduleName]?.containsKey(lecture) == true ){
                        Icon(Icons.Default.Check, "Passed")
                    }
                    DefaultText(lecture)
                }
            }
        }
    }

    @Composable
    fun AlertOnConfirmRewrite(
        isShown: MutableState<Boolean>,
        navigator: Navigator,
        lecture: String
    ) {
        if (isShown.value) {
            AlertDialog(
                onDismissRequest = {isShown.value = false},
                title = { Text("Перепройти тест?") },
                text = { Text("Вы уверены, что хотите перепройти тест? Вы ранее его проходили") },
                confirmButton = {
                    TextButton(onClick = {
                        navigator.push(LectureScreen(moduleName, lecture))
                        isShown.value = false
                    }) {
                        Text("Да")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { isShown.value = false }) {
                        Text("Отмена")
                    }
                }
            )
        }
    }
}