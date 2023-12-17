package com.example.papproject.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import com.example.papproject.datasource.FirebaseDataSource
import com.example.papproject.model.LectureModule
import com.example.papproject.tabs.ProfileTab
import com.example.papproject.tabs.TestsTab
import com.example.papproject.ui.theme.montserratFontFamily
import kotlinx.coroutines.launch

class HomeScreen: Screen {
    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    override fun Content() {
        val tabNavigator = LocalTabNavigator.current
        Column(Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Home screen",
                Modifier
                .height(50.dp),
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = montserratFontFamily,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                )
                )
            Text("You can navigate across screens",
                Modifier
                    .width(146.dp)
                    .height(50.dp),
                style = MaterialTheme.typography.bodySmall)
            Row(Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally)
                .padding(all = 5.dp)
            ){
                Button(
                    onClick = {
                        tabNavigator.current = TestsTab
                    }
                ){
                    Text("To tests")
                }
                Button(
                    onClick = {
                        tabNavigator.current = ProfileTab
                    }
                ){
                    Text("To profile")
                }
            }
            val data = FirebaseDataSource.getModules()

            val scope = rememberCoroutineScope()
            var emptyList by remember { mutableStateOf(listOf(LectureModule("1", listOf("226","446")))) }

            scope.launch {
                data.collect { value ->
                    emptyList = value
                }
            }

            LazyColumn(
                Modifier.fillMaxSize()
            ) {
                items(emptyList){
                    it.LectureElement()
                }
            }



        }
    }
}