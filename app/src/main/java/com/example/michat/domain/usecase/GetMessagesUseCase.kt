package com.example.michat.domain.usecase

import com.example.michat.domain.model.Message
import com.example.michat.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(
    private val messageRepository: MessageRepository
) {
    operator fun invoke(): Flow<List<Message>> {
        return messageRepository.getMessages()
    }
}
