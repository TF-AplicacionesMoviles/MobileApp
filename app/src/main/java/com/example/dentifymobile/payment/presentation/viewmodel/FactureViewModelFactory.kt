package com.example.dentifymobile.payment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dentifymobile.payment.data.repository.FactureRepository

class FactureViewModelFactory(private val factureRepository: FactureRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FactureViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FactureViewModel(factureRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
