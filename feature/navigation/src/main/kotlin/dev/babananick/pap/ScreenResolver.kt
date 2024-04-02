package dev.babananick.pap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import cafe.adriel.voyager.navigator.Navigator
import com.google.firebase.auth.FirebaseUser

@Composable
fun ScreenResolver(
    user: MutableState<FirebaseUser?>,
) {
    if (user.value != null) {
        Navigator(PAPScreen())
    } else {
        Navigator(AuthorizationScreen())
    }
}

