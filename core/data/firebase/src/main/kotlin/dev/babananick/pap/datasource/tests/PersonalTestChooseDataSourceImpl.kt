package dev.babananick.pap.datasource.tests

import com.google.firebase.database.*
import dev.babananick.pap.modules.TestModule
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class PersonalTestChooseDataSourceImpl @Inject constructor(
    private val dataBase: FirebaseDatabase
) : PersonalTestChooseDataSource {
    override fun receiveTests(): Flow<List<TestModule>> = callbackFlow{
        val questionsReference: DatabaseReference = dataBase
            .getReference("pap/rus/test_groups")
        val eventListener = object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                val modules = data.getValue<List<TestModule>>()!!

                if (trySend(modules).isSuccess){
                    close()
                }
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