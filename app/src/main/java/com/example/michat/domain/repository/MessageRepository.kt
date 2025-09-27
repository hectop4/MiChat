package com.example.michat.domain.repository

import com.example.michat.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    suspend fun sendMessage(message: Message)
    fun getMessages(): Flow<List<Message>>
}
