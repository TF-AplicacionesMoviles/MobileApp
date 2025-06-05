package com.example.dentifymobile.inventory.items.domain.usecases

import com.example.dentifymobile.inventory.items.domain.model.Item
import com.example.dentifymobile.inventory.items.domain.repository.ItemRepository

class GetAllItemsUseCase(private val repository: ItemRepository) {
    suspend operator fun invoke(): List<Item>{
        return repository.getAllItemsByUserId()
    }
}