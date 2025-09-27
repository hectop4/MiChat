package com.example.michat.data.repository

import android.content.SharedPreferences
import com.example.michat.data.local.dao.MessageDao
import com.example.michat.data.mapper.toDomain
import com.example.michat.data.mapper.toEntity
import com.example.michat.data.remote.FirebaseMessageService
import com.example.michat.data.remote.mapper.toFirebaseMap
import com.example.michat.data.remote.mapper.toMessage
import com.example.michat.domain.model.Message
import com.example.michat.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
    private val messageDao: MessageDao,
    private val firebaseService: FirebaseMessageService,
    private val sharedPreferences: SharedPreferences
) : MessageRepository {

    companion object {
        private const val USER_NAME_KEY = "user_name"
    }

    override suspend fun sendMessage(message: Message) {
        val messageWithId = message.copy(
            id = if (message.id.isEmpty()) UUID.randomUUID().toString() else message.id
        )

        // Enviar a Firebase (backend)
        firebaseService.sendMessage(messageWithId.toFirebaseMap())

        // Guardar localmente como cache
        messageDao.insertMessage(messageWithId.toEntity())
    }

    override fun getMessages(): Flow<List<Message>> {
        // Obtener el usuario actual desde SharedPreferences directamente
        val currentUserName = sharedPreferences.getString(USER_NAME_KEY, "") ?: ""

        // Obtener mensajes en tiempo real desde Firebase
        return firebaseService.getMessagesFlow().map { firebaseMessages ->
            val messages = firebaseMessages.map { it.toMessage(currentUserName) }

            // Sincronizar con cache local
            messages.forEach { message ->
                try {
                    messageDao.insertMessage(message.toEntity())
                } catch (e: Exception) {
                    // Ignorar errores de duplicados
                }
            }

            messages
        }
    }
}
