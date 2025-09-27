package com.example.michat.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.michat.domain.model.Message
import com.example.michat.domain.usecase.GetCurrentUserUseCase
import com.example.michat.domain.usecase.GetMessagesUseCase
import com.example.michat.domain.usecase.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessagesUseCase: GetMessagesUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    private var currentUser: String = ""

    init {
        loadCurrentUser()
        loadMessages()
    }

    private fun loadCurrentUser() {
        viewModelScope.launch {
            val user = getCurrentUserUseCase()
            currentUser = user?.name ?: ""
            _uiState.value = _uiState.value.copy(currentUserName = currentUser)
        }
    }

    private fun loadMessages() {
        viewModelScope.launch {
            getMessagesUseCase()
                .catch { exception ->
                    _uiState.value = _uiState.value.copy(
                        errorMessage = "Error al cargar mensajes: ${exception.message}"
                    )
                }
                .collect { messages ->
                    val messagesWithUserFlag = messages.map { message ->
                        message.copy(isFromCurrentUser = message.senderName == currentUser)
                    }
                    _uiState.value = _uiState.value.copy(
                        messages = messagesWithUserFlag,
                        isLoading = false
                    )
                }
        }
    }

    fun updateMessageText(text: String) {
        _uiState.value = _uiState.value.copy(
            messageText = text,
            isSendEnabled = text.isNotBlank()
        )
    }

    fun sendMessage() {
        val messageText = _uiState.value.messageText.trim()
        if (messageText.isBlank() || currentUser.isBlank()) return

        _uiState.value = _uiState.value.copy(isSending = true)

        viewModelScope.launch {
            try {
                val message = Message(
                    content = messageText,
                    senderName = currentUser,
                    isFromCurrentUser = true
                )
                sendMessageUseCase(message)
                _uiState.value = _uiState.value.copy(
                    messageText = "",
                    isSending = false,
                    isSendEnabled = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isSending = false,
                    errorMessage = "Error al enviar mensaje: ${e.message}"
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}

data class ChatUiState(
    val messages: List<Message> = emptyList(),
    val messageText: String = "",
    val currentUserName: String = "",
    val isLoading: Boolean = true,
    val isSending: Boolean = false,
    val isSendEnabled: Boolean = false,
    val errorMessage: String? = null
)
