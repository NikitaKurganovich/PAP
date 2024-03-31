package dev.babananick.pap.datasource.tests

import com.google.firebase.database.*
import dev.babananick.pap.questions.LectureWithRightAnswer
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class LectureTestDataSourceImpl @Inject constructor(
    private val dataBase: FirebaseDatabase,
) : LectureTestDataSource {
    override fun receiveLectureQuestions(moduleName: String, submoduleName: String):
            Flow<List<LectureWithRightAnswer>> = callbackFlow {
        val questionsReference: DatabaseReference = dataBase
            .getReference("pap/rus/lectures/$moduleName/modules/$submoduleName/questions")
        val eventListener = object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                val lectureQuestions = data.children.mapNotNull { snapshot ->
                    snapshot.getValue(LectureWithRightAnswer::class.java)?.let {
                        LectureWithRightAnswer(
                            questionText = snapshot.child("question").value as String,
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
    }
}
