package com.example.dentifymobile.payment.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State  // Importante usar el State de Compose
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.dentifymobile.payment.domain.model.Facture
import com.example.dentifymobile.payment.data.repository.FactureRepository

class FactureViewModel(private val factureRepository: FactureRepository) : ViewModel() {

    // Usamos el State de Jetpack Compose
    private val _factures = mutableStateOf<List<Facture>>(emptyList())
    val factures: State<List<Facture>> = _factures

    init {
        loadFactures()
    }

    private fun loadFactures() {
        viewModelScope.launch {
            _factures.value = factureRepository.getFactures()
        }
    }

    // Función para obtener una factura por su ID
    fun getFactureById(id: String): Facture? {
        return _factures.value.find { it.id == id }
    }
}
