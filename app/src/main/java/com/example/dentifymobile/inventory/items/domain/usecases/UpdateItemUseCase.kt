package com.example.dentifymobile.inventory.items.domain.usecases

import com.example.dentifymobile.inventory.items.domain.model.Item
import com.example.dentifymobile.inventory.items.domain.repository.ItemRepository

class UpdateItemUseCase(private val itemRepository: ItemRepository) {
    suspend operator fun invoke(id: Long, item: Item): Item {
        return itemRepository.updateItem(id, item)
    }
}