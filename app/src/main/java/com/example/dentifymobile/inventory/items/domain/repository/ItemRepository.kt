package com.example.dentifymobile.inventory.items.domain.repository

import com.example.dentifymobile.inventory.items.domain.model.Item

interface ItemRepository {
    suspend fun getAllItemsByUserId(): List<Item>
    suspend fun createItem(item: Item): Item
    suspend fun updateItem(id: Long, item: Item): Item
    suspend fun deleteItem(id: Long)
}