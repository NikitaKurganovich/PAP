package dev.babananick.pap.datasource.tests

import com.google.firebase.database.*
import dev.babananick.pap.tests.Test
import dev.babananick.pap.tests.TestWithLeadScale
import dev.babananick.pap.tests.TestWithSharedVariants
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class PersonalTestDataSourceImpl @Inject constructor(
    private val dataBase: FirebaseDatabase,
) : PersonalTestDataSource {
    override fun receiveTest(testId: String): Flow<Test> = flow {
        val testsReference: DatabaseReference = dataBase
            .getReference("pap/rus/personal_tests/$testId")

        val snapshot = suspendCoroutine { continuation ->
            val eventListener = object : ValueEventListener {
                override fun onDataChange(data: DataSnapshot) {
                    continuation.resume(data)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    continuation.resumeWithException(databaseError.toException())
                }
            }
            testsReference.addListenerForSingleValueEvent(eventListener)
        }
        val testType: String = snapshot.child("test_type").getValue<String>() ?: "null"
        val testToReturn = when (testType) {
            "LEAD_SCALE" -> {
                snapshot.getValue<TestWithLeadScale>()!!
            }

            "SHARED_VARIANTS" -> {
                snapshot.getValue<TestWithSharedVariants>()!!
            }

            else -> {
                error("No such test!")
            }
        }
        emit(testToReturn)
    }
}