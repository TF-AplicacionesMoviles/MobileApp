package com.example.dentifymobile.inventory.items.domain.usecases

import com.example.dentifymobile.inventory.items.domain.repository.ItemRepository

class DeleteItemUseCase(private val repository: ItemRepository) {
    suspend operator fun invoke(id: Long) {
        return repository.deleteItem(id)
    }
}