package com.example.papproject.util

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object Hash {
    fun getSHA(input: String): String? {
        try {
            val md = MessageDigest.getInstance("SHA-256")
            val messageDigest = md.digest(input.toByteArray())
            val num = BigInteger(1, messageDigest)
            var hashText = num.toString(16)
            while (hashText.length < 32) {
                hashText = "0$hashText"
            }
            return hashText
        } catch (ex: NoSuchAlgorithmException) {
            println("Exception Occured: ${ex.message}")
            return null
        }
    }
}