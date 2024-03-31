package dev.babananick.pap.datasource.lecture

import com.google.firebase.database.*
import dev.babananick.pap.LectureModule
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class LectureChooseDataSourceImpl @Inject constructor(
    private val dataBase: FirebaseDatabase,
) : LectureChooseDataSource {

    override fun receiveLectureModules(): Flow<List<LectureModule>> = callbackFlow {
        val questionsReference: DatabaseReference = dataBase.getReference("pap/rus/lectures")
        val eventListener = object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                val lectureModules = data.children.mapNotNull { snapshot ->
                    snapshot.getValue(LectureModule::class.java)?.let { module ->
                        LectureModule(
                            module_name = module.module_name,
                            submodulesNames = snapshot.child("submodules")
                                .children.map { it.child("submodule_name").value as String }
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
}