package dev.babananick.pap.datasource.lecture

import com.google.firebase.database.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class LecturesDataSourceImpl @Inject constructor(
    private val dataBase: FirebaseDatabase,
) : LecturesDataSource {
    override fun receiveLectureTheory(moduleName: String, submoduleName: String): Flow<String> {
        return callbackFlow {
            val questionsReference: DatabaseReference = dataBase
                .getReference("pap/rus/academic_material/$moduleName/modules/$submoduleName/theory")
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
}