package dev.babananick.pap.datasource

import com.google.firebase.database.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class PersonalTestChooseDataSourceImpl @Inject constructor(
    private val dataBase: FirebaseDatabase
) : PersonalTestChooseDataSource {
    override val personalTests: Flow<List<String>>
        get() = callbackFlow {
            val questionsReference: DatabaseReference = dataBase
                .getReference("pap/rus/personal_tests")
            val eventListener = object : ValueEventListener {
                override fun onDataChange(data: DataSnapshot) {
                    val personalTests = data.children.mapNotNull { snapshot ->
                        snapshot.child("test_name").value as String
                    }
                    trySend(personalTests).isSuccess
                    close()
                }

                override fun onCancelled(p0: DatabaseError) {
                    close()
                }
            }
            questionsReference.addValueEventListener(eventListener)
            awaitClose { questionsReference.removeEventListener(eventListener) }
        }

    override fun receiveTests(): Flow<List<String>> {
        return personalTests
    }

}