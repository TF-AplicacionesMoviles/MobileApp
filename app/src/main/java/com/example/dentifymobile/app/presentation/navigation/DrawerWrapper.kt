package com.example.dentifymobile.app.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AirlineSeatFlatAngled
import androidx.compose.material.icons.filled.ContentPasteSearch
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun DrawerWrapper(navController: NavController, content: @Composable (Modifier) -> Unit) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onItemSelected = { route ->
                    scope.launch {
                        drawerState.close()
                        navController.navigate(route)
                    }
                }
            )
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(onClick = {
                                scope.launch { drawerState.open() }
                            }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menú")
                            }

                            Icon(
                                imageVector = Icons.Default.Favorite, // Icono de la app (puedes cambiarlo)
                                contentDescription = "App Logo",
                                modifier = Modifier.size(24.dp)
                            )

                            IconButton(onClick = {
                                // Acción al presionar notificaciones
                            }) {
                                Icon(Icons.Default.Notifications, contentDescription = "Notificaciones")
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFD1F2EB),
                        titleContentColor = Color.Black
                    )
                )
            }
        ) { padding ->
            content(Modifier.padding(padding))
        }
    }
}




@Composable
fun DrawerContent(
    onItemSelected: (String) -> Unit
) {
    ModalDrawerSheet(
        drawerContainerColor = Color(0xFFD1F2EB),
        drawerContentColor = Color.Black
    ) {
        Text(
            text = "Menú",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold
        )
        NavigationDrawerItem(
            label = { Text("Appointments",
                fontWeight = FontWeight.Bold
            ) },
            icon = { Icon(Icons.Default.ContentPasteSearch, contentDescription = null) },
            selected = false,
            onClick = { onItemSelected("appointments") },
        )
        NavigationDrawerItem(
            label = { Text("Patients",
                fontWeight = FontWeight.Bold
            ) },
            icon = { Icon(Icons.Default.AirlineSeatFlatAngled, contentDescription = null) },
            selected = false,
            onClick = { onItemSelected("patientAttention") }
        )
        NavigationDrawerItem(
            label = { Text("Inventory",
                fontWeight = FontWeight.Bold
            ) },
            icon = { Icon(Icons.Default.Inventory, contentDescription = null) },
            selected = false,
            onClick = { onItemSelected("inventory") }
        )
        NavigationDrawerItem(
            label = { Text("Payments",
                fontWeight = FontWeight.Bold
            ) },
            icon = { Icon(Icons.Default.CreditCard, contentDescription = null) },
            selected = false,
            onClick = { onItemSelected("payments") }
        )
        NavigationDrawerItem(
            label = { Text("Profile",
                fontWeight = FontWeight.Bold) },
            icon = { Icon(Icons.Default.AccountBalance, contentDescription = null) },
            selected = false,
            onClick = { onItemSelected("profile") }
        )
        NavigationDrawerItem(
            label = { Text("Dashboard",
                fontWeight = FontWeight.Bold
            ) },
            icon = { Icon(Icons.Default.Dashboard, contentDescription = null) },
            selected = false,
            onClick = { onItemSelected("dashboard") }
        )
    }
}