package com.example.dentifymobile.inventory.items.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dentifymobile.inventory.items.domain.model.Item
import com.example.dentifymobile.inventory.items.presentation.viewmodel.ItemFormViewModel

@Composable
fun ItemFormView(viewModel: ItemFormViewModel, toItems: () -> Unit, toBack: () -> Unit) {
    val initialItem = viewModel.itemState.value

    val name = remember { mutableStateOf("") }
    val price = remember { mutableDoubleStateOf(0.0) }
    val stockQuantity = remember { mutableIntStateOf(0) }
    val isActive = remember { mutableStateOf(true) }
    val category = remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }
    val errorMessage = remember { mutableStateOf("") }

    LaunchedEffect(initialItem) {
        initialItem?.let {
            name.value = it.name
            price.doubleValue = it.price
            stockQuantity.intValue = it.stockQuantity
            isActive.value = it.isActive
            category.value = it.category
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.clearItem()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 72.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = if (initialItem == null) "New Item" else "Edit Item",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    CreateItemTextField("Name", name.value) { name.value = it }
                    CreateItemTextField("Quantity", stockQuantity.intValue) { stockQuantity.intValue = it }
                    CategoryDropdownMenu(selectedCategory = category.value) { category.value = it }
                    CreateItemTextField("Price", price.doubleValue) { price.doubleValue = it }

                    if (errorMessage.value != "") {
                        Text(
                            text = errorMessage.value,
                            color = Color.Red,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (
                        name.value.isBlank() ||
                        price.doubleValue == 0.0 ||
                        stockQuantity.intValue == 0 ||
                        category.value.isBlank()
                    ) {
                        errorMessage.value = "All fields must be completed before saving."
                    } else {
                        val isEditing = initialItem != null
                        val item = Item(
                            id = if (isEditing) initialItem!!.id else -1L,
                            name = name.value,
                            price = price.doubleValue,
                            stockQuantity = stockQuantity.intValue,
                            isActive = true,
                            category = category.value
                        )
                        if (isEditing) {
                            viewModel.updateItem(item.id, item)
                        } else {
                            viewModel.item(item)
                        }
                        errorMessage.value = ""
                        showDialog.value = true
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2C3E50)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text(if (initialItem == null) "Register" else "Save", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Text(
                text = "Return to the general inventory screen",
                color = Color(0xFF2C3E50),
                modifier = Modifier
                    .clickable { toBack() }
                    .padding(top = 12.dp),
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        }

        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                confirmButton = {
                    Button(onClick = {
                        showDialog.value = false
                        toItems()
                    }) {
                        Text("OK")
                    }
                },
                text = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .background(Color(0xFFDFF6F3), RoundedCornerShape(16.dp))
                            .padding(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp).padding(bottom = 16.dp),
                            tint = Color(0xFF1C1C1C)
                        )
                        Text(
                            text = if (initialItem == null) "Product registered correctly" else "Product modified successfully",
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                shape = RoundedCornerShape(24.dp)
            )
        }
    }
}

@Composable
fun <T> CreateItemTextField(label: String, value: T, onValueChange: (T) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        OutlinedTextField(
            value = value.toString(),
            onValueChange = { input ->
                val newValue: T? = try {
                    when (value) {
                        is Int -> input.toIntOrNull() as T?
                        is Double -> input.toDoubleOrNull() as T?
                        else -> input as T
                    }
                } catch (e: Exception) {
                    null
                }
                if (newValue != null) {
                    onValueChange(newValue)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Gray.copy(alpha = 0.3f),
                focusedIndicatorColor = Color(0xFF2C3E50)
            )
        )
    }
}

@Composable
fun CategoryDropdownMenu(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    val options = listOf(
        "DENTAL_INSTRUMENTS", "DISPOSABLE_SUPPLIES", "CLEANING_AND_DISINFECTION",
        "RESTORATIVE_MATERIALS", "ORTHODONTIC_SUPPLIES", "ENDODONTIC_SUPPLIES",
        "SURGICAL_SUPPLIES", "PROTECTIVE_EQUIPMENT", "IMAGING_EQUIPMENT",
        "CONSUMABLES", "MEDICATIONS"
    )

    val expanded = remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = "Category", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
        ) {
            OutlinedTextField(
                value = selectedCategory,
                onValueChange = {},
                readOnly = true,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded.value = true },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Gray.copy(alpha = 0.3f),
                    focusedIndicatorColor = Color(0xFF2C3E50)
                )
            )
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.background(Color.White)
        ) {
            options.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category) },
                    onClick = {
                        onCategorySelected(category)
                        expanded.value = false
                    }
                )
            }
        }
    }
}