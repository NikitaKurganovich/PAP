package dev.babananick.pap.core.data.firebase.datasource.tests

import com.google.firebase.database.*
import dev.babananick.pap.core.model.modules.TestModule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class PersonalTestChooseDataSourceImpl @Inject constructor(
    private val dataBase: FirebaseDatabase
) : PersonalTestChooseDataSource {
    override fun receiveTests(): Flow<List<TestModule>> = flow{
        val questionsReference: DatabaseReference = dataBase
            .getReference("pap/rus/test_groups")

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

        val testModules: List<TestModule> = snapshot.getValue<List<TestModule>>()
            ?: error("Failed to download tests")

        emit(testModules)
    }

}