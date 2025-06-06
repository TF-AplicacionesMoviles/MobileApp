package com.example.dentifymobile.inventory.items.data.repository

import android.util.Log
import com.example.dentifymobile.inventory.items.data.model.toItem
import com.example.dentifymobile.inventory.items.data.remote.dto.ItemRequest
import com.example.dentifymobile.inventory.items.data.remote.dto.UpdateRequest
import com.example.dentifymobile.inventory.items.data.remote.services.ItemService
import com.example.dentifymobile.inventory.items.domain.model.Item
import com.example.dentifymobile.inventory.items.domain.repository.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemRepositoryImpl(
    private val apiService: ItemService
): ItemRepository {
    override suspend fun getAllItemsByUserId(): List<Item> {
        val response = apiService.getAllItems()
        if (response.isSuccessful) {
            val body = response.body()
            Log.d("ItemRepositoryImpl", "Raw response: $body")
            return body?.map { it.toItem() } ?: emptyList()
        } else {
            throw Exception("Error fetching items: ${response.code()} ${response.message()}")
        }
    }

    override suspend fun createItem(item: Item): Item {
        val response = apiService.createItem(
            ItemRequest(
                name = item.name,
                price = item.price,
                stockQuantity = item.stockQuantity,
                category = item.category
            )
        )
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return body.toItem()
            } else {
                throw Exception("Response body is null")
            }
        } else {
            throw Exception("Error creating item: ${response.code()} ${response.message()}")
        }
    }

    override suspend fun updateItem(id: Long, item: Item) = withContext(Dispatchers.IO) {
        val response = apiService.updateItem(id,
            updateRequest = UpdateRequest(
                name = item.name,
                price = item.price,
                stockQuantity = item.stockQuantity,
                category = item.category,
                isActive = item.isActive
            )
        )
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return@withContext body.toItem()
            } else {
                throw Exception("Response body is null")
            }
        } else {
            throw Exception("Error updating item: ${response.code()} ${response.message()}")
        }
    }

    override suspend fun deleteItem(id: Long) {
        val response = apiService.deleteItem(id)
        if (!response.isSuccessful) {
            throw Exception("Error deleting item: ${response.code()} ${response.message()}")
        }
    }
}