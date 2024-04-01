package dev.babananick.pap.datasource.tests

import com.google.firebase.database.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class PersonalTestChooseDataSourceImpl @Inject constructor(
    private val dataBase: FirebaseDatabase
) : PersonalTestChooseDataSource {
    override fun receiveTests(): Flow<List<String>> = callbackFlow{
        val questionsReference: DatabaseReference = dataBase
            .getReference("pap/rus/personal_tests")

        val eventListener = object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                val personalTests = data.children.mapNotNull { snapshot ->
                    snapshot.child("name").value as? String
                }
                trySend(personalTests).isSuccess
            }

            override fun onCancelled(p0: DatabaseError) {
                close()
            }
        }
        questionsReference.addListenerForSingleValueEvent(eventListener)
        questionsReference.addValueEventListener(eventListener)
        awaitClose { questionsReference.removeEventListener(eventListener) }
    }

}