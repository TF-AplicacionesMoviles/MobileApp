package com.example.dentifymobile.inventory.items.presentation.di

import android.content.Context
import com.example.dentifymobile.inventory.items.data.di.DataModule
import com.example.dentifymobile.inventory.items.presentation.viewmodel.ItemFormViewModel
import com.example.dentifymobile.inventory.items.presentation.viewmodel.ItemsViewModel

object PresentationModule {
    fun getItemsViewModel(context: Context): ItemsViewModel {
        return ItemsViewModel(
            DataModule.getAllItemsUseCase(context),
            DataModule.deleteItemUseCase(context)
        )
    }

    fun getItemFormViewModel(context: Context): ItemFormViewModel {
        return ItemFormViewModel(
            DataModule.createItemUseCase(context),
            DataModule.updateItemUseCase(context)
        )
    }
}