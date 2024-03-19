package dev.babananick.pap.datasource

import com.google.firebase.database.*
import dev.babananick.pap.LectureQuestion
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class LectureTestDataSourceImpl @Inject constructor(
    private val dataBase: FirebaseDatabase
) : LectureTestDataSource {
    override fun receiveLectureQuestions(moduleName: String, submoduleName: String): Flow<List<LectureQuestion>> {
        return callbackFlow {
            val questionsReference: DatabaseReference = dataBase
                .getReference("pap/rus/lectures/$moduleName/modules/$submoduleName/questions")
            val eventListener = object : ValueEventListener {
                override fun onDataChange(data: DataSnapshot) {
                    val lectureQuestions = data.children.mapNotNull { snapshot ->
                        snapshot.getValue(LectureQuestion::class.java)?.let {
                            LectureQuestion(
                                question = snapshot.child("question").value as String,
                                correctAnswer = snapshot.child("correct_answers").value as String,
                                availableAnswers = snapshot.child("available_answers")
                                    .children.map { it.value as String }
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
        }    }

}