package com.example.dentifymobile.iam.data.remote.dto

data class UpdatePasswordRequest(
    val newPassword: String,
    val oldPassword: String
)
