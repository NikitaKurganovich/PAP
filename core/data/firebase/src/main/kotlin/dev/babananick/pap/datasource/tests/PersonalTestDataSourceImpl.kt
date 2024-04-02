package dev.babananick.pap.datasource.tests

import com.google.firebase.FirebaseException
import com.google.firebase.database.*
import dev.babananick.pap.tests.Test
import dev.babananick.pap.tests.TestWithLeadScale
import dev.babananick.pap.tests.TestWithSharedVariants
import kotlinx.coroutines.cancel
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
                            val testToReturn: Test = when (test) {
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
                                //TODO Rewrite to single read
                                else -> {
                                    error("No such test")
                                }
                            }
                            trySend(testToReturn)
                        }
                    }
                }
                close()
            }

            override fun onCancelled(p0: DatabaseError) {
                println("SUS ${p0.message}")
                close()
                error("$p0")
            }
        }
        testsReference.addValueEventListener(eventListener)
        awaitClose { testsReference.removeEventListener(eventListener) }
    }
}