package com.example.pap.datasource

import com.example.pap.model.EmotionalIntelligenceQuestion
import com.example.pap.model.LectureModule
import com.example.pap.model.LectureQuestion
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
            .getReference("rus/lectures")
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
                close()
            }
        }
        questionsReference.addValueEventListener(eventListener)
        awaitClose { questionsReference.removeEventListener(eventListener) }
    }
    override val personalTests: Flow<List<String>> = callbackFlow {
        val questionsReference: DatabaseReference = databaseReference
            .getReference("rus/personal_tests")
        val eventListener = object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                val personalTests = data.children.mapNotNull { snapshot ->
                    snapshot.key
                }
                trySend(personalTests).isSuccess
                close()
            }
            override fun onCancelled(p0: DatabaseError) {
                close()
            }
        }
        questionsReference.addValueEventListener(eventListener)
        awaitClose { questionsReference.removeEventListener(eventListener) }
    }


    override fun getLectureQuestions(moduleName: String, submoduleName: String): Flow<List<LectureQuestion>> {
        return callbackFlow {
            val questionsReference: DatabaseReference = databaseReference
                .getReference("rus/lectures/$moduleName/modules/$submoduleName/questions")
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
                    close()
                }
            }
            questionsReference.addValueEventListener(eventListener)
            awaitClose { questionsReference.removeEventListener(eventListener) }
        }
    }

    override fun getPersonalTestQuestions(testName: String): Flow<List<EmotionalIntelligenceQuestion>> {
        return callbackFlow {
            val testReference: DatabaseReference = databaseReference
                .getReference("rus/personal_tests/$testName")
            val eventListener = object : ValueEventListener {
                override fun onDataChange(data: DataSnapshot) {
                    val answerVariants = data.child("answer_variants")
                        .getValue(object : GenericTypeIndicator<HashMap<String, Int>>() {})
                        ?.toList()?.sortedBy { (_, value) -> value }?.toMap() as HashMap<String, Int>

                    val personalQuestions = data.child("questions").children.mapNotNull { snapshot ->
                        val questionText = snapshot.child("question").getValue(String::class.java)
                        val relatedScale = snapshot.child("related_scale").getValue(String::class.java)

                        if (questionText != null && relatedScale != null && answerVariants != null) {
                            EmotionalIntelligenceQuestion(questionText, relatedScale, answerVariants)
                        } else {
                            null
                        }
                    }
                    trySend(personalQuestions).isSuccess
                }

                override fun onCancelled(p0: DatabaseError) {
                    close()
                }
            }
            testReference.addValueEventListener(eventListener)
            awaitClose { testReference.removeEventListener(eventListener) }
        }
    }


    override fun getModules(): Flow<List<LectureModule>> {
        return lectureModules
    }

    override fun getTests(): Flow<List<String>> {
       return personalTests
    }

    override fun getLectureTheory(moduleName: String, submoduleName: String): Flow<String> {
        return callbackFlow {
            val questionsReference: DatabaseReference = databaseReference
                .getReference("rus/lectures/$moduleName/modules/$submoduleName/theory")
            val eventListener = object : ValueEventListener {
                override fun onDataChange(data: DataSnapshot) {
                    val lectureTheory = data.getValue(String::class.java) ?: ""
                    trySend(lectureTheory).isSuccess
                }

                override fun onCancelled(p0: DatabaseError) {
                    close()
                }
            }
            questionsReference.addValueEventListener(eventListener)
            awaitClose { questionsReference.removeEventListener(eventListener) }
        }
    }

    override fun getUserResults(callback: (HashMap<String, HashMap<String, Int>>) -> Unit) {
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val modules = document.data as HashMap<String, HashMap<String, Int>>
                    callback(modules)
                } else {
                    callback(hashMapOf())
                }
            }
            .addOnFailureListener {
                callback(hashMapOf())
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

    override fun upsertPersonalResults(testName: String, results: HashMap<String,Int>) {
        val docData = hashMapOf(
            testName to results
        )
        docRef.set(docData, SetOptions.merge())
    }

}