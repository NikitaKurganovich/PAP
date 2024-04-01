package dev.babananick.pap.datasource.tests

import com.google.firebase.database.*
import dev.babananick.pap.tests.Test
import dev.babananick.pap.tests.TestWithLeadScale
import dev.babananick.pap.tests.TestWithSharedVariants
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class PersonalTestDataSourceImpl @Inject constructor(
    private val dataBase: FirebaseDatabase,
) : PersonalTestDataSource {
    override fun receiveTest(testName: String): Flow<Test> = callbackFlow {
        val testsReference: DatabaseReference = dataBase
            .getReference("pap/rus/personal_tests")
        val eventListener = object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                data.children.forEach { snapshot ->
                    snapshot.getValue(Test::class.java)?.let { test: Test ->
                        if (test.name == testName) {
                            val testToReturn = when (test) {
                                is TestWithLeadScale -> {
                                    TestWithLeadScale(
                                        questions = test.questions,
                                        name = testName,
                                        interpretation = test.interpretation
                                    )
                                }
                                is TestWithSharedVariants -> {
                                    TestWithSharedVariants(
                                        questions = test.questions,
                                        name = testName,
                                        answer_variants = test.answer_variants,
                                        interpretation = test.interpretation
                                    )
                                }
                                else -> {
                                    error("Unknown test type")
                                }
                            }
                            trySend(testToReturn)
                        }
                    }
                }
                close()
            }

            override fun onCancelled(p0: DatabaseError) {
                close()
            }
        }
        testsReference.addValueEventListener(eventListener)
        awaitClose { testsReference.removeEventListener(eventListener) }
    }
}