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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dentifymobile.inventory.items.domain.model.Item
import com.example.dentifymobile.inventory.items.presentation.viewmodel.ItemsViewModel

@Composable
fun ItemsView(viewModel: ItemsViewModel, toItemForm: (Item?) -> Unit) {
    val items = viewModel.items.collectAsState()

    val showDialog = remember {
        mutableStateOf(false)
    }
    val itemToDelete = remember {
        mutableStateOf<Long?>(null)
    }

    LaunchedEffect(Unit) {
        viewModel.getAll()
    }

    val currentPage = remember { mutableIntStateOf(0) }
    val itemsPerPage = 5
    val totalItems = items.value.size
    val totalPages = (totalItems + itemsPerPage - 1) / itemsPerPage
    val startIndex = currentPage.intValue * itemsPerPage
    val endIndex = minOf(startIndex + itemsPerPage, totalItems)
    val pagedItems = items.value.subList(startIndex, endIndex)

    val headerBackgroundColor = Color(0xFF2C3E50)
    val rowBackgroundColor = Color(0xFFD1F2EB)

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .weight(1f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Inventory",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = headerBackgroundColor
                )

                Button(
                    onClick = { toItemForm(null) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD1F2EB),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Add new product")
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .background(headerBackgroundColor)
                            .padding(vertical = 12.dp, horizontal = 16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(40.dp))
                        Text(
                            "Name",
                            modifier = Modifier.weight(1.4f),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                        Text(
                            "Stock",
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                        Text(
                            "Category",
                            modifier = Modifier.weight(2f),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                        Text(
                            "Price",
                            modifier = Modifier.weight(0.8f),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                    }

                    HorizontalDivider(thickness = 2.dp)

                    LazyColumn(
                        modifier = Modifier
                            .heightIn(max = 400.dp)
                            .background(rowBackgroundColor)
                            .padding(top = 5.dp)
                    ) {
                        itemsIndexed(pagedItems) { index, _ ->
                            val actualIndex = startIndex + index
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = items.value[actualIndex].isSelected,
                                    onCheckedChange = { checked ->
                                        viewModel.toggleItemSelected(actualIndex, checked) },
                                    modifier = Modifier
                                        .weight(0.5f)
                                        .padding(end = 8.dp)
                                )
                                Text(
                                    text = items.value[actualIndex].name,
                                    modifier = Modifier.weight(1.4f),
                                    maxLines = 1
                                )
                                Text(
                                    text = items.value[actualIndex].stockQuantity.toString(),
                                    modifier = Modifier.weight(1f),
                                    maxLines = 1
                                )
                                Text(
                                    text = items.value[actualIndex].category,
                                    modifier = Modifier.weight(2f),
                                    maxLines = 1
                                )
                                Text(
                                    text = "$${items.value[actualIndex].price}",
                                    modifier = Modifier.weight(0.8f),
                                    maxLines = 1
                                )
                            }
                            HorizontalDivider()
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                if (currentPage.intValue > 0) currentPage.intValue--
                                      },
                            enabled = currentPage.intValue > 0,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2C3E50)),
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Text("Previous", color = Color.White)
                        }

                        Text(
                            text = "Page ${currentPage.intValue + 1} of $totalPages",
                            modifier = Modifier.align(Alignment.CenterVertically),
                            color = Color.Black
                        )

                        Button(
                            onClick = {
                                if (currentPage.intValue < totalPages - 1) currentPage.intValue++ },
                            enabled = currentPage.intValue < totalPages - 1,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2C3E50)),
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Text("Next", color = Color.White)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                val selectedItem = items.value.find { it.isSelected }
                                if (selectedItem != null) {
                                    toItemForm(selectedItem)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF2C3E50),
                                contentColor = Color.White
                            )
                        ) {
                            Text("Modify")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            onClick = {
                                val selectedItem = items.value.find { it.isSelected }
                                if (selectedItem != null) {
                                    itemToDelete.value = selectedItem.id
                                    showDialog.value = true
                                } },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF2C3E50),
                                contentColor = Color.White
                            )
                        ) {
                            Text("Delete")
                        }
                    }
                }
                if (showDialog.value) {
                    AlertDialog(
                        onDismissRequest = { showDialog.value = false },
                        confirmButton = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Button(
                                    onClick = {
                                        showDialog.value = false
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFAEE1DC),
                                        contentColor = Color.Black
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text("Cancel")
                                }

                                Button(
                                    onClick = {
                                        itemToDelete.value?.let { id ->
                                            viewModel.deleteItem(id)
                                            viewModel.getAll()
                                        }
                                        showDialog.value = false
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFAEE1DC),
                                        contentColor = Color.Black
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text("Accept")
                                }
                            }
                        },
                        containerColor = Color(0xFF2C3E50),
                        textContentColor = Color.White,
                        shape = RoundedCornerShape(16.dp),
                        title = null,
                        text = {
                            Text(
                                text = "Are you sure you want to remove this product from inventory?",
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp)
                                    .wrapContentHeight(),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center
                            )
                        }
                    )
                }
            }
        }
    }
}