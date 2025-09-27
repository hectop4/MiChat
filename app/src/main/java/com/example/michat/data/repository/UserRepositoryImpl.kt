package com.example.michat.data.repository

import android.content.SharedPreferences
import com.example.michat.domain.model.User
import com.example.michat.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : UserRepository {

    companion object {
        private const val USER_NAME_KEY = "user_name"
    }

    override suspend fun saveUser(user: User) {
        sharedPreferences.edit()
            .putString(USER_NAME_KEY, user.name)
            .apply()
    }

    override suspend fun getCurrentUser(): User? {
        val userName = sharedPreferences.getString(USER_NAME_KEY, null)
        return userName?.let { User(it) }
    }
}
