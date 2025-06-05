package com.example.dentifymobile.inventory.items.presentation.navigation

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dentifymobile.inventory.items.presentation.di.PresentationModule
import com.example.dentifymobile.inventory.items.presentation.view.ItemFormView
import com.example.dentifymobile.inventory.items.presentation.view.ItemsView

fun NavGraphBuilder.inventoryNavGraph(navController: NavController, context: Context) {
    val itemsViewModel = PresentationModule.getItemsViewModel(context)
    val itemFormViewModel = PresentationModule.getItemFormViewModel(context)

    navigation(startDestination = "items", route = "inventory") {
        composable(route = "items") {
            ItemsView(
                itemsViewModel,
                toItemForm = { item ->
                    if (item != null) {
                        itemFormViewModel.setItemState(item)
                    }
                    navController.navigate("itemForm")
                }
            )
        }

        composable(route = "itemForm") {
            ItemFormView(
                itemFormViewModel,
                toBack = {
                    navController.popBackStack()
                },
                toItems = {
                    navController.navigate("items")
                }
            )
        }
    }
}