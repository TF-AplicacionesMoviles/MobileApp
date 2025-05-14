package com.example.dentifymobile.iam.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentifymobile.iam.domain.model.Login
import com.example.dentifymobile.iam.domain.usecases.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var loginState by mutableStateOf<Login?>(null)
        private set

    var errorMessage by mutableStateOf<String?>(null)

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = loginUseCase(username, password)
                loginState = response
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }
}