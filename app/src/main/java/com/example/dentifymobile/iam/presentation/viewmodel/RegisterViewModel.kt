package com.example.dentifymobile.iam.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentifymobile.iam.domain.model.Login
import com.example.dentifymobile.iam.domain.model.Register
import com.example.dentifymobile.iam.domain.usecases.RegisterUseCase
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    var registerState by mutableStateOf<Login?>(null)
        private set

    var errorMessage by mutableStateOf<String?>(null)

    val isAuthenticated: Boolean
        get() = registerState != null

    fun register(
        firstName: String,
        lastName: String,
        email: String,
        companyName: String,
        username: String,
        password: String,
        trial: Boolean
    ) {
        viewModelScope.launch {
            try {
                val result = registerUseCase(
                    Register(firstName, lastName, email, companyName, username, password, trial)
                )
                registerState = result
                errorMessage = null
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }
}
