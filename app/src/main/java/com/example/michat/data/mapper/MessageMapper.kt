package com.example.michat.data.mapper

import com.example.michat.data.local.entity.MessageEntity
import com.example.michat.domain.model.Message

fun MessageEntity.toDomain(): Message {
    return Message(
        id = id,
        content = content,
        senderName = senderName,
        timestamp = timestamp,
        isFromCurrentUser = isFromCurrentUser
    )
}

fun Message.toEntity(): MessageEntity {
    return MessageEntity(
        id = id,
        content = content,
        senderName = senderName,
        timestamp = timestamp,
        isFromCurrentUser = isFromCurrentUser
    )
}
