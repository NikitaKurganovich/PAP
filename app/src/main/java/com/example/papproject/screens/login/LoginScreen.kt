package com.example.papproject.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import cafe.adriel.voyager.core.screen.Screen
import com.example.papproject.util.Hash
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import java.lang.Exception

class LoginScreen: Screen {
    @Composable
    override fun Content() {
        val db = Firebase.firestore
        val auth = Firebase.auth
        var loginText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }
        var message by remember { mutableStateOf("") }

        Column{
            TextField(
                value = loginText,
                onValueChange = {
                    loginText = it
                },
                placeholder = { Text(text = "Entre your login") }
            )
            TextField(
                value = passwordText,
                onValueChange = {
                    passwordText = it
                },
                placeholder = { Text(text = "Entre your password") }
            )
            Button(
                onClick = {
                    db.collection("Users")
                        .get()
                        .addOnSuccessListener { result ->
                            for (document in result){
                                if (document.id == loginText){
                                    message = if (Hash.getSHA(passwordText) == document.data["password"] as String)
                                        "You are $loginText"
                                    else "Incorrect login or password"
                                    if(Hash.getSHA(passwordText) == document.data["password"] as String){
                                        auth.signInAnonymously()
                                            .addOnCompleteListener{
                                                if(it.isSuccessful){
                                                    val user = auth.currentUser
                                                }
                                            }
                                    }
                                }
                            }
                        }
                        .addOnFailureListener{
                            println(it.message)
                        }

                }
            ){
                Text("Check")
            }
            Text(message)
       }
    }
}