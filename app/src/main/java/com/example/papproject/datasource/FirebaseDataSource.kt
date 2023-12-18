package com.example.papproject.datasource

import android.content.ContentValues.TAG
import android.util.Log
import com.example.papproject.model.LectureModule
import com.example.papproject.model.LectureQuestion
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

object FirebaseDataSource : DataSource {
    private val databaseReference = FirebaseDatabase.getInstance()

    private val docRef = Firebase.firestore.collection("users").document(Firebase.auth.currentUser?.uid ?: "")

    override val lectureModules = callbackFlow {
        val questionsReference: DatabaseReference = databaseReference
            .getReference("lectures")
        val eventListener = object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                val lectureModules = data.children.mapNotNull { snapshot ->
                    snapshot.getValue(LectureModule::class.java)?.let {
                        LectureModule(
                            moduleName = snapshot.key!!,
                            submodulesNames = snapshot.child("modules").children.map { it.key ?: "" }
                        )
                    }
                }
                trySend(lectureModules).isSuccess
                close()
            }
            override fun onCancelled(p0: DatabaseError) {
                println(p0.message)
                close()
            }
        }
        questionsReference.addValueEventListener(eventListener)
        awaitClose { questionsReference.removeEventListener(eventListener) }
    }


    override fun getLectureQuestions(moduleName: String, submoduleName: String): Flow<List<LectureQuestion>> {
        return callbackFlow {
            val questionsReference: DatabaseReference = databaseReference
                .getReference("lectures/$moduleName/modules/$submoduleName/questions")
            val eventListener = object : ValueEventListener {
                override fun onDataChange(data: DataSnapshot) {
                    val lectureQuestions = data.children.mapNotNull { snapshot ->
                        snapshot.getValue(LectureQuestion::class.java)?.let {
                            LectureQuestion(
                                question = it.question,
                                correct_answer = it.correct_answer,
                                available_answers = it.available_answers
                            )
                        }
                    }
                    println(lectureQuestions)
                    println(moduleName)
                    println(submoduleName)
                    trySend(lectureQuestions).isSuccess
                }

                override fun onCancelled(p0: DatabaseError) {
                    println(p0.message)
                    close()
                }
            }
            questionsReference.addValueEventListener(eventListener)
            awaitClose { questionsReference.removeEventListener(eventListener) }
        }
    }

    override fun getModules(): Flow<List<LectureModule>> {
        return lectureModules
    }

    override fun getLectureTheory(moduleName: String, submoduleName: String): Flow<String> {
        return callbackFlow {
            val questionsReference: DatabaseReference = databaseReference
                .getReference("lectures/$moduleName/modules/$submoduleName/theory")
            val eventListener = object : ValueEventListener {
                override fun onDataChange(data: DataSnapshot) {
                    val lectureTheory = data.getValue(String::class.java) ?: ""
                    trySend(lectureTheory).isSuccess
                }

                override fun onCancelled(p0: DatabaseError) {
                    println(p0.message)
                    close()
                }
            }
            questionsReference.addValueEventListener(eventListener)
            awaitClose { questionsReference.removeEventListener(eventListener) }
        }
    }

    override fun getUserResults(callback: (Map<String, Int>) -> Unit) {
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val modules = document.data as Map<String, Int>
                    callback(modules)
                } else {
                    callback(hashMapOf())
                }
            }
            .addOnFailureListener { exception ->
                callback(hashMapOf())
                Log.d(TAG, "get failed with ", exception)
            }
    }

    override fun upsertResults(moduleName: String, submoduleName: String, score: Int) {
        val nestedMap = hashMapOf(
            submoduleName to score
        )
        val docData = hashMapOf(
            moduleName to nestedMap
        )
        docRef.set(docData, SetOptions.merge())
    }

}