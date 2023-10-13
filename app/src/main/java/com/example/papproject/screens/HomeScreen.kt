package com.example.papproject.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.papproject.R
import com.example.papproject.tabs.HomeTab

class HomeScreen: Screen {
    @Composable
    override fun Content() {
        val provider = GoogleFont.Provider(
            providerAuthority = "com.google.android.gms.fonts",
            providerPackage = "com.google.android.gms",
            certificates = R.array.com_google_android_gms_fonts_certs

        )

        val navigator = LocalNavigator.currentOrThrow
        Column(Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Home screen",
                Modifier
                .height(50.dp),
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = FontFamily(
                        Font(googleFont = GoogleFont("Montserrat"), fontProvider = provider)
                    ),
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
                        navigator.replace(TestScreen())
                    }
                ){
                    Text("To tests")
                }
                Button(
                    onClick = {
                        navigator.replace(ProfileScreen())
                    }
                ){
                    Text("To profile")
                }
            }
        }
    }
}