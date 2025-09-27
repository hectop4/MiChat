package com.example.michat.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.michat.domain.model.User
import com.example.michat.domain.usecase.GetCurrentUserUseCase
import com.example.michat.domain.usecase.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    init {
        checkIfUserExists()
    }

    private fun checkIfUserExists() {
        viewModelScope.launch {
            val user = getCurrentUserUseCase()
            if (user != null) {
                _uiState.value = _uiState.value.copy(
                    navigateToChat = true
                )
            }
        }
    }

    fun updateUserName(name: String) {
        _uiState.value = _uiState.value.copy(
            userName = name,
            isLoginEnabled = name.isNotBlank()
        )
    }

    fun login() {
        if (_uiState.value.userName.isBlank()) return

        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            try {
                val user = User(_uiState.value.userName.trim())
                saveUserUseCase(user)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    navigateToChat = true
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Error al guardar usuario"
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    fun onNavigatedToChat() {
        _uiState.value = _uiState.value.copy(navigateToChat = false)
    }
}

data class LoginUiState(
    val userName: String = "",
    val isLoading: Boolean = false,
    val isLoginEnabled: Boolean = false,
    val navigateToChat: Boolean = false,
    val errorMessage: String? = null
)
