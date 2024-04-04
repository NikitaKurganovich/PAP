package dev.babananick.pap.datasource.tests

import com.google.firebase.database.*
import dev.babananick.pap.tests.TestWithRightAnswer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LectureTestDataSourceImpl @Inject constructor(
    private val dataBase: FirebaseDatabase,
) : LectureTestDataSource {
    override fun receiveLectureQuestions( submoduleName: String):
            Flow<TestWithRightAnswer> = flow {
        val questionsReference: DatabaseReference = dataBase
            .getReference("pap/rus/lectures/$submoduleName/questions")
        val snapshot = suspendCoroutine { continuation ->
            val eventListener = object : ValueEventListener {
                override fun onDataChange(data: DataSnapshot) {
                    continuation.resume(data)
                }

                override fun onCancelled(p0: DatabaseError) {
                    continuation.resumeWithException(p0.toException())
                }
            }
            questionsReference.addListenerForSingleValueEvent(eventListener)
        }
        val questions: TestWithRightAnswer = snapshot.getValue<TestWithRightAnswer>()
            ?: error("Failed to download the test")
        emit(questions)
    }
}
