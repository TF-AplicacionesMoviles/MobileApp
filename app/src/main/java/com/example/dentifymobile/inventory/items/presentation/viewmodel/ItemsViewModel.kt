package com.example.dentifymobile.inventory.items.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentifymobile.inventory.items.domain.model.Item
import com.example.dentifymobile.inventory.items.domain.usecases.DeleteItemUseCase
import com.example.dentifymobile.inventory.items.domain.usecases.GetAllItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ItemsViewModel(
    private val getAllItemsUseCase: GetAllItemsUseCase,
    private val deleteItemUseCase: DeleteItemUseCase
): ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items
    var errorMessage by mutableStateOf<String?>(null)
    fun getAll() {
        viewModelScope.launch {
            try {
                val result = getAllItemsUseCase()
                println("Fetched items: $result") // O usa Log.d
                _items.value = result
            } catch (e: Exception) {
                errorMessage = e.message
                println("Error fetching items: ${e.message}")
            }
        }
    }

    fun deleteItem(id: Long) {
        viewModelScope.launch {
            try {
                deleteItemUseCase(id)
                getAll()
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }
}