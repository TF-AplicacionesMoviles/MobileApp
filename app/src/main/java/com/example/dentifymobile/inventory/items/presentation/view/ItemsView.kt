package com.example.dentifymobile.inventory.items.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.dentifymobile.inventory.items.domain.model.Item
import com.example.dentifymobile.inventory.items.presentation.viewmodel.ItemsViewModel

@Composable
fun ItemsView(viewModel: ItemsViewModel, toItemForm: (Item?) -> Unit) {
    val items = viewModel.items.collectAsState()

    val showDialog = remember { mutableStateOf(false) }
    val itemToDelete = remember { mutableStateOf<Item?>(null) }

    LaunchedEffect(Unit) {
        viewModel.getAll()
    }

    val filteredItems = items.value

    Box(modifier = Modifier.fillMaxSize().padding(top = 20.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 72.dp, start = 16.dp, end = 16.dp)
        ) {
            LazyColumn {
                items(filteredItems) { item ->
                    ItemCardView(
                        item = item,
                        onEdit = { toItemForm(item) },
                        onDelete = {
                            itemToDelete.value = item
                            showDialog.value = true
                        }
                    )
                }
            }
        }

        Button(
            onClick = { toItemForm(null) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2C3E50)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp)
                .fillMaxWidth(0.9f)
        ) {
            Text("Add New Product", color = Color.White, fontWeight = FontWeight.Bold)
        }

        if (showDialog.value) {
            Dialog(onDismissRequest = { showDialog.value = false }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 60.dp, horizontal = 20.dp)
                        .background(Color(0xFF26323D), shape = RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Are you sure you want to delete this product?",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "This action cannot be undone",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = { showDialog.value = false },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                            ) {
                                Text("Cancel", color = Color.Black)
                            }
                            Button(
                                onClick = {
                                    itemToDelete.value?.let { viewModel.deleteItem(it.id) }
                                    showDialog.value = false
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD9D9D9))
                            ) {
                                Text("Accept", color = Color.Black)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCardView(
    item: Item,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp),
            ) {
                IconButton(onClick = { onEdit() }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.Black)
                }
                IconButton(onClick = { onDelete() }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Black)
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(text = "Stock: ${item.stockQuantity}", fontSize = 14.sp, color = Color.Gray)
                Text(text = "Category: ${item.category}", fontSize = 14.sp)
                Text(text = "Price: $${item.price}", fontSize = 14.sp)
            }
        }
    }
}
