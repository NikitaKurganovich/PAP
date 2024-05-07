package dev.babananick.pap.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import coil.ImageLoader
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint
import dev.babananick.pap.feature.navigation.ScreenResolver
import dev.babananick.pap.ui.components.LocalImageLoaderAmbient
import dev.babananick.pap.ui.theme.PAPTheme
import javax.inject.Inject

@AndroidEntryPoint
class PAPActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    @Inject lateinit var imageLoader: ImageLoader
    override fun onCreate(savedInstanceState: Bundle?) {
        //Firebase.database.setPersistenceEnabled(true)
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val user = remember { mutableStateOf(auth.currentUser)}
            DisposableEffect(Unit) {
                val listener = FirebaseAuth.AuthStateListener {
                    user.value = it.currentUser
                }
                auth.addAuthStateListener(listener)
                onDispose { auth.removeAuthStateListener(listener) }
            }
            CompositionLocalProvider(LocalImageLoaderAmbient provides imageLoader) {
                PAPTheme {
                    ScreenResolver(user)
                }
            }
        }
    }
}

