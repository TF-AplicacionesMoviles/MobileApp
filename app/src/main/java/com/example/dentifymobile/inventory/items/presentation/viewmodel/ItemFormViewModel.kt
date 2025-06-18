package com.example.dentifymobile.inventory.items.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentifymobile.inventory.items.domain.model.Item
import com.example.dentifymobile.inventory.items.domain.usecases.CreateItemUseCase
import com.example.dentifymobile.inventory.items.domain.usecases.UpdateItemUseCase
import kotlinx.coroutines.launch

class ItemFormViewModel(
    private val createItemUseCase: CreateItemUseCase,
    private val updateItemUseCase: UpdateItemUseCase
): ViewModel() {
    var itemState = mutableStateOf<Item?>(null)

    fun item(item: Item) {
        viewModelScope.launch {
            createItemUseCase(item)
        }
    }

    fun updateItem(id: Long, item: Item) {
        viewModelScope.launch {
            updateItemUseCase(id, item)
        }
    }

    fun setItemState(item: Item) {
        itemState.value = item
    }

    fun clearItem() {
        itemState.value = null
    }
}