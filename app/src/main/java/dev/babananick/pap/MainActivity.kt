package dev.babananick.pap

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
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
import dev.babananick.pap.screens.LoginScreen
import dev.babananick.pap.tabs.HomeTab
import dev.babananick.pap.tabs.ProfileTab
import dev.babananick.pap.tabs.TestsTab
import dev.babananick.pap.ui.theme.Green40
import dev.babananick.pap.ui.theme.PAPTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dev.babananick.pap.util.DefaultText

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        Firebase.database.setPersistenceEnabled(true)
        auth = Firebase.auth
        super.onCreate(savedInstanceState)

        setContent {
            var user by remember { mutableStateOf(auth.currentUser)}

            DisposableEffect(Unit) {
                val listener = FirebaseAuth.AuthStateListener {
                    user = it.currentUser
                }
                auth.addAuthStateListener(listener)
                onDispose { auth.removeAuthStateListener(listener) }
            }
            PAPTheme {
                if(user != null){
                    Content()
                } else{
                    Navigator(LoginScreen())
                }
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
                            DefaultText(text = it.current.options.title)
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.primary)
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

@HiltAndroidApp
class MainApplication : Application() {
}



