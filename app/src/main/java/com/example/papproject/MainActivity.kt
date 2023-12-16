package com.example.papproject

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.papproject.screens.LoginScreen
import com.example.papproject.tabs.HomeTab
import com.example.papproject.tabs.ProfileTab
import com.example.papproject.tabs.TestsTab
import com.example.papproject.ui.theme.Green40
import com.example.papproject.ui.theme.PAPProjectTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        // Initialize Firebase Auth
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContent {
            val showDialog = remember { mutableStateOf(false) }
            var user by remember { mutableStateOf(auth.currentUser)}

            DisposableEffect(Unit) {
                val listener = FirebaseAuth.AuthStateListener {
                    user = it.currentUser
                }
                auth.addAuthStateListener(listener)
                onDispose { auth.removeAuthStateListener(listener) }
            }
            PAPProjectTheme {
                if(user != null){
                    Content()
                } else{
                    Navigator(LoginScreen())
                }
            }


            // Listen for network changes
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onLost(network: Network) {
                    showDialog.value = true
                }

                override fun onAvailable(network: Network) {
                    showDialog.value = false
                }
            }

            // Register the callback to listen for network changes
            connectivityManager.registerDefaultNetworkCallback(networkCallback)

            // Remember to unregister the callback when it's no longer needed
            DisposableEffect(Unit) {
                onDispose { connectivityManager.unregisterNetworkCallback(networkCallback) }
            }

            if (showDialog.value) {
                AlertDialog(
                    onDismissRequest = { showDialog.value = false },
                    title = { Text("Отсутствует подключение к сети") },
                    text = { Text("Пожалуйста, проверьте своё подключение к интернету и повторите попытку") },
                    confirmButton = {
                        Button(onClick = { connectivityManager.unregisterNetworkCallback(networkCallback)   }) {
                            Text("Переподключиться")
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun Content(){
        TabNavigator(HomeTab){
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = it.current.options.title)
                        }
                    )
                },
                content = {
                    Column(modifier = Modifier
                        .padding(it)) {
                        CurrentTab()
                    }
                },
                bottomBar = {
                    BottomNavigation(
                        contentColor = Green40
                    ) {
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(TestsTab)
                        TabNavigationItem(ProfileTab)
                    }
                }
            )
        }
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current

        BottomNavigationItem(
            modifier = Modifier.background(MaterialTheme.colorScheme.primary),
            selected = tabNavigator.current.key == tab.key,
            onClick = { tabNavigator.current = tab },
            icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
        )
    }
}


