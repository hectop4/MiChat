package com.example.michat.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseMessageService @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    companion object {
        private const val MESSAGES_COLLECTION = "messages"
    }

    suspend fun sendMessage(messageData: Map<String, Any>) {
        firestore.collection(MESSAGES_COLLECTION)
            .add(messageData)
            .await()
    }

    fun getMessagesFlow(): Flow<List<Map<String, Any>>> = callbackFlow {
        val listener = firestore.collection(MESSAGES_COLLECTION)
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val messages = snapshot?.documents?.mapNotNull { document ->
                    document.data?.plus("id" to document.id)
                } ?: emptyList()

                trySend(messages)
            }

        awaitClose { listener.remove() }
    }
}
