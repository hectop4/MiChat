package com.example.michat.domain.model

data class Message(
    val id: String = "",
    val content: String,
    val senderName: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isFromCurrentUser: Boolean = false
)
