package com.example.dentifymobile.iam.domain.model

data class Register (
    val firstName: String,
    val lastName: String,
    val email: String,
    val companyName: String,
    val username: String,
    val password: String,
    val trial: Boolean
)