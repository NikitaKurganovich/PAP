package com.example.papproject.datasource

import com.example.papproject.model.LectureQuestion
import com.google.firebase.database.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

object FirebaseDataSource : DataSource {
    private val databaseReference = FirebaseDatabase.getInstance()
    override fun getLectureQuestions(moduleName: String, testName: String): Flow<List<LectureQuestion>> {
        return callbackFlow {
            val questionsReference: DatabaseReference = databaseReference
                .getReference("lectures/modules/$moduleName/tests/$testName/questions")
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
                    trySend(lectureQuestions)
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

}