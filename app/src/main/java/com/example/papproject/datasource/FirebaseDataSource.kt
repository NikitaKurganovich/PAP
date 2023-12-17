package com.example.papproject.datasource

import com.example.papproject.model.LectureModule
import com.example.papproject.model.LectureQuestion
import com.google.firebase.database.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

object FirebaseDataSource : DataSource {
    private val databaseReference = FirebaseDatabase.getInstance()

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
    override fun getLectureQuestions(moduleName: String, testName: String): Flow<List<LectureQuestion>> {
        return callbackFlow {
            val questionsReference: DatabaseReference = databaseReference
                .getReference("lectures/$moduleName/tests/$testName/questions")
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

}