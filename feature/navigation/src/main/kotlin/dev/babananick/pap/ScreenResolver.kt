package dev.babananick.pap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import cafe.adriel.voyager.navigator.Navigator
import coil.ImageLoader
import com.google.firebase.auth.FirebaseUser

@Composable
fun ScreenResolver(
    user: MutableState<FirebaseUser?>,
    imageLoader: ImageLoader
) {
    if (user.value != null) {
        Navigator(PAPScreen(imageLoader))
    } else {
        Navigator(AuthorizationScreen())
    }
}

