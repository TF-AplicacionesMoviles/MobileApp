package com.example.dentifymobile.iam.data.remote.dto

data class RegisterRequest (
    val firstName: String,
    val lastName: String,
    val email: String,
    val companyName: String,
    val username: String,
    val password: String,
    val trial: Boolean
)