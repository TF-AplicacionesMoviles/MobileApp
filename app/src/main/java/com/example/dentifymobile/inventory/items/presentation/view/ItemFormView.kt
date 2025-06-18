package com.example.dentifymobile.inventory.items.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.dentifymobile.R
import com.example.dentifymobile.inventory.items.domain.model.Item
import com.example.dentifymobile.inventory.items.presentation.viewmodel.ItemFormViewModel

@Composable
fun ItemFormView(viewModel: ItemFormViewModel,
                 toItems: () -> Unit,
                 toBack: () -> Unit) {
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
            .background(Color(0xFFEAFAF1))
            .padding(top = 40.dp,
                start = 40.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.form_icon),
                contentDescription = "Form Logo",
                modifier = Modifier
                    .size(80.dp)
                    .padding(bottom = 16.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFD1F2EB))
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(if (initialItem == null) "New Item" else "Modify Item", fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(8.dp))
                    CreateItemTextField("Name", name.value) { name.value = it }
                    CreateItemTextField("Quantity", stockQuantity.intValue) { stockQuantity.intValue = it }
                    CategoryDropdownMenu(
                        selectedCategory = category.value,
                        onCategorySelected = { category.value = it }
                    )
                    CreateItemTextField("Price", price.doubleValue) { price.doubleValue = it }
                    isActive.value = true

                    if (errorMessage.value != "") {
                        Text(
                            text = errorMessage.value,
                            color = Color.Red,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp, top = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = {
                            if (
                                name.value.isBlank() ||
                                price.doubleValue == 0.0 ||
                                stockQuantity.intValue == 0 ||
                                !isActive.value ||
                                category.value.isBlank()
                            ) {
                                errorMessage.value = "All fields must be completed before saving."
                            } else {
                                val isEditing = viewModel.itemState.value != null
                                val item = Item(
                                    id = if (isEditing) viewModel.itemState.value!!.id else -1L,
                                    name = name.value,
                                    price = price.doubleValue,
                                    stockQuantity = stockQuantity.intValue,
                                    isActive = isActive.value,
                                    category = category.value
                                )
                                println("isActive before saving: ${isActive.value}")
                                if (isEditing) {
                                    viewModel.updateItem(item.id, item)
                                } else {
                                    viewModel.item(item)
                                }
                                errorMessage.value = ""
                                toItems()
                            }
                            showDialog.value = true
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2C3E50)),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth(0.6f)
                    ) {
                        Text(if (initialItem == null) "Register" else "Save", color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Return to the general inventory screen",
                        color = Color(0xFF2C3E50),
                        modifier = Modifier
                            .clickable { toBack() }
                            .padding(top = 8.dp),
                        fontWeight = FontWeight.Medium,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }

            if (showDialog.value) {
                val successMessage = if (initialItem == null) {
                    "Product registered correctly"
                } else {
                    "Product modified successfully"
                }

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
                                modifier = Modifier
                                    .size(64.dp)
                                    .padding(bottom = 16.dp),
                                tint = Color(0xFF1C1C1C)
                            )
                            Text(
                                text = successMessage,
                                style = MaterialTheme.typography.bodyLarge,
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
}

@Composable
fun <T> CreateItemTextField(label: String, value: T, onValueChange: (T) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(text = label, fontWeight = FontWeight.Bold)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(2.dp)
        ) {
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
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent
                )
            )
        }
    }
}
@Composable
fun CategoryDropdownMenu(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    val options = listOf(
        "DENTAL_INSTRUMENTS",
        "DISPOSABLE_SUPPLIES",
        "CLEANING_AND_DISINFECTION",
        "RESTORATIVE_MATERIALS",
        "ORTHODONTIC_SUPPLIES",
        "ENDODONTIC_SUPPLIES",
        "SURGICAL_SUPPLIES",
        "PROTECTIVE_EQUIPMENT",
        "IMAGING_EQUIPMENT",
        "CONSUMABLES",
        "MEDICATIONS"
    )

    val expanded = remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(text = "Category", fontWeight = FontWeight.Bold)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .clickable { expanded.value = true }
                .padding(12.dp)
        ) {
            Text(text = selectedCategory.ifBlank { "Select a category" })
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier
                .background(Color.White)
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
