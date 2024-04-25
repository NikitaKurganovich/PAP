package dev.babananick.pap.core.data.firebase.datasource.lecture

import com.google.firebase.database.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LectureChooseDataSourceImpl @Inject constructor(
    private val dataBase: FirebaseDatabase,
) : LectureChooseDataSource {

    override fun receiveLectureModules(): Flow<List<dev.babananick.pap.core.model.modules.LectureModule>> = flow {
        val questionsReference: DatabaseReference = dataBase
            .getReference("pap/rus/academic_modules")

        val snapshot = suspendCoroutine { continuation ->
            val eventListener = object : ValueEventListener {
                override fun onDataChange(data: DataSnapshot) {
                    continuation.resume(data)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    continuation.resumeWithException(databaseError.toException())
                }
            }
            questionsReference.addListenerForSingleValueEvent(eventListener)
        }

        val lectures: List<dev.babananick.pap.core.model.modules.LectureModule> = snapshot.getValue<List<dev.babananick.pap.core.model.modules.LectureModule>>()!!
        emit(lectures)
    }
}