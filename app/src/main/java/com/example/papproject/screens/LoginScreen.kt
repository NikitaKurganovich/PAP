package com.example.papproject.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import cafe.adriel.voyager.core.screen.Screen
import com.example.papproject.util.Hash
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class LoginScreen: Screen {
    @Composable
    override fun Content() {
        val db = Firebase.firestore
        var loginText by remember { mutableStateOf("Entre login") }
        var passwordText by remember { mutableStateOf("Entre password") }
        var correctPassword = ""
        var message by remember { mutableStateOf("") }

        Column{
            TextField(
                loginText,
                onValueChange = {
                    loginText = it
                }
            )
            TextField(
                passwordText,
                onValueChange = {
                    passwordText = it
                }
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