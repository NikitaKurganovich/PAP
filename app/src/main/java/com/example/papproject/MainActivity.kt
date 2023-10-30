package com.example.papproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.papproject.screens.login.LoginScreen
import com.example.papproject.tabs.HomeTab
import com.example.papproject.tabs.ProfileTab
import com.example.papproject.tabs.TestsTab
import com.example.papproject.util.EnvironmentCheck
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val showDialog = remember { mutableStateOf(false) }
            val auth = Firebase.auth
            var user by remember { mutableStateOf(auth.currentUser)}

            DisposableEffect(Unit) {
                val listener = FirebaseAuth.AuthStateListener {
                    user = it.currentUser
                }
                auth.addAuthStateListener(listener)
                onDispose { auth.removeAuthStateListener(listener) }
            }

            if(user != null){
                Content()
            } else{
                Navigator(LoginScreen())
            }

            if (!EnvironmentCheck.isOnline(this)) {
                showDialog.value = true
            }
            if (showDialog.value) {
                AlertDialog(
                    onDismissRequest = { showDialog.value = false },
                    title = { Text("No Internet Connection") },
                    text = { Text("Please check your internet connection and try again.") },
                    confirmButton = {
                        Button(onClick = { showDialog.value = false }) {
                            Text("Try reconnect")
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
                    BottomNavigation {
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
            selected = tabNavigator.current.key == tab.key,
            onClick = { tabNavigator.current = tab },
            icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
        )
    }
}

fun some(){
    val auth = Firebase.auth

    auth.signInAnonymously()
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                val user = auth.currentUser
                // updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                println(task.exception?.message)
                // updateUI(null)
            }
        }
}


