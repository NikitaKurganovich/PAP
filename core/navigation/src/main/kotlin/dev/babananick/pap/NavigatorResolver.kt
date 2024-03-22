package dev.babananick.pap

import cafe.adriel.voyager.core.screen.Screen
import com.google.firebase.auth.FirebaseUser

fun resolveNavigation(user: FirebaseUser?): Screen {
    return if (user != null) {
        AuthorizationScreen()
    } else {
        PAPScreen()
    }
}
