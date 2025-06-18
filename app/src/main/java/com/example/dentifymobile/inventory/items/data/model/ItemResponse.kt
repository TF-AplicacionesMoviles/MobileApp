package com.example.dentifymobile.inventory.items.data.model

import com.example.dentifymobile.inventory.items.domain.model.Item

data class ItemResponse(
    val id: Long?,
    val name: String?,
    val price: Double?,
    val stockQuantity: Int?,
    val category: String?,
    var isActive: Boolean?
)

fun ItemResponse.toItem(): Item {
    return Item(
        id = id ?: 0,
        name = name ?: "",
        price = price ?: 0.0,
        stockQuantity = stockQuantity ?: 0,
        category = category ?: "",
        isActive = isActive ?: false
    )
}