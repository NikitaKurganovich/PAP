package dev.babananick.pap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import dagger.hilt.android.AndroidEntryPoint
import dev.babananick.pap.theme.PAPTheme

@AndroidEntryPoint
class PAPActivity : ComponentActivity() {
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
                Navigator(resolveNavigation(user))
            }
        }
    }
}
