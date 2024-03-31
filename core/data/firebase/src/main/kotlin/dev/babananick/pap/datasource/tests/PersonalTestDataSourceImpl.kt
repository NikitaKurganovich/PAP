package dev.babananick.pap.datasource.tests

import com.google.firebase.database.*
import dev.babananick.pap.AnswerVariant
import dev.babananick.pap.questions.QuestionWithScale
import dev.babananick.pap.questions.QuestionWithVariants
import dev.babananick.pap.tests.PersonalTest
import dev.babananick.pap.tests.PersonalTestWithLeadScale
import dev.babananick.pap.tests.PersonalTestWithSharedVariants
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class PersonalTestDataSourceImpl @Inject constructor(
    private val dataBase: FirebaseDatabase
): PersonalTestDataSource {
    override fun receiveTest(testName: String): Flow<PersonalTest> = callbackFlow {
        val testsReference: DatabaseReference = dataBase
            .getReference("pap/rus/personal_tests")
        val eventListener = object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                data.children.forEach { snapshot ->
                    if (snapshot.child("name").value as? String == testName) {
                        val questions = snapshot.child("questions").children.mapNotNull { questionSnapshot ->
                            val questionText = questionSnapshot.child("question").value as? String
                            val relatedScale = questionSnapshot.child("related_scale").value as? String
                            if (questionSnapshot.hasChild("answer_variants")) {
                                val answerVariants = questionSnapshot.child("answer_variants").children.mapNotNull { variantSnapshot ->
                                    val answerText = variantSnapshot.child("answer").value as? String
                                    val scale = variantSnapshot.child("related_scale").value as? String
                                    if (answerText != null && scale != null) AnswerVariant(answerText, scale) else null
                                }
                                if (questionText != null) QuestionWithVariants(questionText, answerVariants) else null
                            } else {
                                if (questionText != null && relatedScale != null) QuestionWithScale(questionText, relatedScale) else null
                            }
                        }
                        val answerVariants = snapshot.child("answer_variants").children.mapNotNull { variantSnapshot ->
                            val answerText = variantSnapshot.child("answer_text").value as? String
                            val relatedScore = variantSnapshot.child("related_score").value as? String
                            if (answerText != null && relatedScore != null) AnswerVariant(answerText, relatedScore) else null
                        }
                        val modifiedTest = if (snapshot.hasChild("answer_variants")) {
                            PersonalTestWithSharedVariants(questions.filterIsInstance<QuestionWithScale>(), answerVariants)
                        } else {
                            PersonalTestWithLeadScale(questions.filterIsInstance<QuestionWithVariants>())
                        }
                        trySend(modifiedTest).isSuccess
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