package com.example.michat.data.remote.mapper

import com.example.michat.domain.model.Message

fun Message.toFirebaseMap(): Map<String, Any> {
    return mapOf(
        "content" to content,
        "senderName" to senderName,
        "timestamp" to timestamp
    )
}

fun Map<String, Any>.toMessage(currentUserName: String): Message {
    return Message(
        id = this["id"] as? String ?: "",
        content = this["content"] as? String ?: "",
        senderName = this["senderName"] as? String ?: "",
        timestamp = this["timestamp"] as? Long ?: 0L,
        isFromCurrentUser = (this["senderName"] as? String) == currentUserName
    )
}
