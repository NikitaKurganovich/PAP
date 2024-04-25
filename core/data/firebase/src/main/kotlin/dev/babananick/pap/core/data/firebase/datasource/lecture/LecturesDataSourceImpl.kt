package dev.babananick.pap.core.data.firebase.datasource.lecture

import com.google.firebase.database.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LecturesDataSourceImpl @Inject constructor(
    private val dataBase: FirebaseDatabase,
) : LecturesDataSource {
    override fun receiveLectureTheory(submoduleName: String): Flow<String> =
        flow {
            val questionsReference: DatabaseReference = dataBase
                .getReference("pap/rus/lectures/$submoduleName/theory")

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
            val lectureTheory = snapshot.getValue(String::class.java) ?: error("Failed to download theory")
            emit(lectureTheory)
        }

}