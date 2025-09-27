package com.example.michat.domain.repository

import com.example.michat.domain.model.User

interface UserRepository {
    suspend fun saveUser(user: User)
    suspend fun getCurrentUser(): User?
}
