package com.example.dentifymobile.inventory.items.data.remote.dto

data class ItemRequest(
    val name: String,
    val price: Double,
    val stockQuantity: Int,
    val category: String
)
