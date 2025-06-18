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

    val isAuthenticated: Boolean
        get() = loginState != null


    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = loginUseCase(username, password)
                println("ACCESS TOKEN: ${response.accessToken}")
                println("REFRESH TOKEN: ${response.refreshToken}")
                loginState = response
                errorMessage = null
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }
}