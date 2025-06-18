package com.example.dentifymobile.inventory.items.domain.model

data class Item(
    val id: Long,
    val name: String,
    val price: Double,
    val stockQuantity: Int,
    var isActive: Boolean,
    val category: String,
    var isSelected: Boolean = false
)