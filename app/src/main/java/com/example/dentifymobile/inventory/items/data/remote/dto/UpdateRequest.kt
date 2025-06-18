package com.example.dentifymobile.inventory.items.data.remote.dto

data class UpdateRequest(
    val name: String,
    val price: Double,
    val stockQuantity: Int,
    val category: String,
    var isActive: Boolean
)
