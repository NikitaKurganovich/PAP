package dev.babananick.pap.core.data.firebase.datasource.tests

import com.google.firebase.database.*
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
            Flow<dev.babananick.pap.core.model.tests.TestWithRightAnswer> = flow {
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
        val questions: dev.babananick.pap.core.model.tests.TestWithRightAnswer = snapshot.getValue<dev.babananick.pap.core.model.tests.TestWithRightAnswer>()
            ?: error("Failed to download the test")
        emit(questions)
    }
}
