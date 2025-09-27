package com.example.michat.domain.usecase

import com.example.michat.domain.model.User
import com.example.michat.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        userRepository.saveUser(user)
    }
}
