package dev.babananick.pap.datasource.tests

import com.google.firebase.database.*
import dev.babananick.pap.questions.QuestionWithRightAnswer
import dev.babananick.pap.tests.TestWithRightAnswer
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class LectureTestDataSourceImpl @Inject constructor(
    private val dataBase: FirebaseDatabase,
) : LectureTestDataSource {
    override fun receiveLectureQuestions(moduleName: String, submoduleName: String):
            Flow<TestWithRightAnswer> = callbackFlow {
        val questionsReference: DatabaseReference = dataBase
            .getReference("pap/rus/lectures/$moduleName/modules/$submoduleName/questions")
        val eventListener = object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                val lectureQuestions = data.children.mapNotNull { snapshot ->
                    snapshot.getValue(QuestionWithRightAnswer::class.java)?.let { question ->
                        QuestionWithRightAnswer(
                            question = question.question,
                            correct_answer = question.correct_answer,
                            available_answers = question.available_answers
                        )
                    }
                }
                trySend(
                    TestWithRightAnswer(
                        questions = lectureQuestions,
                        name = submoduleName,
                        interpretation = listOf()
                    )
                ).isSuccess
            }

            override fun onCancelled(p0: DatabaseError) {
                close()
            }
        }
        questionsReference.addValueEventListener(eventListener)
        awaitClose { questionsReference.removeEventListener(eventListener) }
    }
}
