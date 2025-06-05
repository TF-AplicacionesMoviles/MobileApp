package com.example.dentifymobile.inventory.items.domain.usecases

import com.example.dentifymobile.inventory.items.domain.model.Item
import com.example.dentifymobile.inventory.items.domain.repository.ItemRepository

class CreateItemUseCase(private val itemRepository: ItemRepository) {
    suspend operator fun invoke(item: Item): Item {
        return itemRepository.createItem(item)
    }
}