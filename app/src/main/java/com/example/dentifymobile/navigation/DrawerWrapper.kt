package com.example.dentifymobile.navigation

import android.content.Context
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dentifymobile.R
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.filled.Logout
import androidx.compose.ui.platform.LocalContext
import com.example.dentifymobile.iam.data.storage.TokenStorage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerWrapper(navController: NavController, content: @Composable () -> Unit) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onItemSelected = { route ->
                    scope.launch {
                        drawerState.close()
                        if (route == "logout") {

                            // Aquí haces la limpieza
                            logoutUser(context, navController)
                        } else {
                            navController.navigate(route)
                        }
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

                            Image(
                                painter = painterResource(id = R.drawable.logo_top_bar),
                                contentDescription = "App Logo",
                                modifier = Modifier.size(36.dp)
                            )

                            IconButton(onClick = { /* Acción de notificación */ }) {
                                Icon(
                                    Icons.Default.Notifications,
                                    contentDescription = "Notificaciones",
                                    tint = Color(0xFF2C3E50)
                                )
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
            Box(modifier = Modifier.padding(padding)) {
                content()
            }
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_top_bar),
                contentDescription = "Logo",
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = "Dentify",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 12.dp),
                color = Color(0xFF2C3E50)
            )
        }
        Text(
            text = "Menu principal",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
            color = Color.DarkGray
        )

        val items = listOf(
            Triple("Dashboard", Icons.Default.Dashboard, "home"),
            Triple("Appointments", Icons.Default.ContentPasteSearch, "appointments"),
            Triple("Patients", Icons.Default.AirlineSeatFlatAngled, "patientAttention"),
            Triple("Inventory", Icons.Default.Inventory, "inventory"),
            Triple("Payments", Icons.Default.CreditCard, "payments"),
            Triple("Profile", Icons.Default.AccountBalance, "profile"),
            Triple("Logout", Icons.Default.Logout, "logout")
        )

        items.forEach { (label, icon, route) ->
            NavigationDrawerItem(
                label = {
                    Text(label, fontWeight = FontWeight.Medium)
                },
                icon = {
                    Icon(icon, contentDescription = label, tint = Color(0xFF2C3E50))
                },
                selected = false,
                onClick = { onItemSelected(route) },
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

fun logoutUser(context: Context, navController: NavController) {

    TokenStorage.clearTokens(context)
    // Limpiar backstack y navegar al login
    navController.navigate("authentication/login") {
        popUpTo("app") { inclusive = true }
    }
}
