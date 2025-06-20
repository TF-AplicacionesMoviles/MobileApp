package com.example.dentifymobile.iam.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentifymobile.iam.data.model.UserInfoResponse
import com.example.dentifymobile.iam.data.remote.dto.UpdateInformationRequest
import com.example.dentifymobile.iam.data.remote.dto.UpdatePasswordRequest
import com.example.dentifymobile.iam.domain.usecases.GetUserInfoUseCase
import com.example.dentifymobile.iam.domain.usecases.UpdateInformationUseCase
import com.example.dentifymobile.iam.domain.usecases.UpdatePasswordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel (
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val updatePasswordUseCase: UpdatePasswordUseCase,
    private val updateInformationUseCase: UpdateInformationUseCase
): ViewModel(){
    private val _userInfo = MutableStateFlow<UserInfoResponse?>(null)
    val userInfo: StateFlow<UserInfoResponse?> = _userInfo

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchUserInfo() {
        viewModelScope.launch {
            try {
                val result = getUserInfoUseCase()
                _userInfo.value = result
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching user info: ${e.message}"
            }
        }
    }

    fun updatePassword(request: UpdatePasswordRequest, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                updatePasswordUseCase(request)
                onSuccess()
            } catch (e: Exception) {
                _errorMessage.value = "Password update failed: ${e.message}"
            }
        }
    }

    fun updateInformation(request: UpdateInformationRequest, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                updateInformationUseCase(request)
                fetchUserInfo()
                onSuccess()
            } catch (e: Exception) {
                _errorMessage.value = "Information update failed: ${e.message}"
            }
        }
    }

    fun setError(message: String) {
        _errorMessage.value = message
    }

    fun clearError() {
        _errorMessage.value = null
    }


}