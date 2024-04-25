package dev.babananick.pap.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import cafe.adriel.voyager.navigator.Navigator
import com.google.firebase.auth.FirebaseUser
import dev.babananick.pap.feature.coordinators.authorization.AuthorizationScreen
import dev.babananick.pap.feature.coordinators.mainscreenspace.PAPScreen

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

