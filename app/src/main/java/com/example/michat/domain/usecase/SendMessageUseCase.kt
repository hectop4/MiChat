package com.example.michat.domain.usecase

import com.example.michat.domain.model.Message
import com.example.michat.domain.repository.MessageRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val messageRepository: MessageRepository
) {
    suspend operator fun invoke(message: Message) {
        messageRepository.sendMessage(message)
    }
}
