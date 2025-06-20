package com.example.dentifymobile.iam.data.remote.dto

data class UpdateInformationRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val companyName: String
)
