package com.example.dentifymobile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AirlineSeatFlatAngled
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.ContentPasteSearch
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dentifymobile.iam.presentation.view.Home
import com.example.dentifymobile.iam.presentation.view.Login
import com.example.dentifymobile.iam.presentation.view.Register
import com.example.dentifymobile.patientattention.patient.presentation.view.PatientFormView
import com.example.dentifymobile.patientattention.patient.presentation.view.PatientsView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DentifyApp() {
    val navController = rememberNavController()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    val navigationItems = listOf(
        NavigationItem(
            icon = Icons.Default.ContentPasteSearch,
            title = "Appointments",
            route = "appointments"
        ),
        NavigationItem(
            icon = Icons.Default.AirlineSeatFlatAngled,
            title = "Patients",
            route = "patients"
        ),
        NavigationItem(
            icon = Icons.Default.Inventory,
            title = "Inventory",
            route = "inventory"
        ),
        NavigationItem(
            icon = Icons.Default.CreditCard,
            title = "Payments",
            route = "payments"
        ),
        NavigationItem(
            icon = Icons.Default.AccountBalance,
            title = "Profile",
            route = "profile"
        ),
        NavigationItem(
            icon = Icons.Default.Dashboard,
            title = "Dashboard",
            route = "dashboard"
        )
    )



//    ModalNavigationDrawer(
//        drawerState = drawerState,
//        drawerContent = {
//            ModalDrawerSheet {
//                Spacer(modifier = Modifier.height(16.dp))
//                navigationItems.forEach { item ->
//                    NavigationDrawerItem(
//                        label = { Text(item.title) },
//                        icon = { Icon(item.icon, contentDescription = item.title) },
//                        selected = false,
//                        onClick = {
//                            scope.launch { drawerState.close() }
//                            navController.navigate(item.route)
//                        },
//                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
//                    )
//                }
//            }
//        }
//    ) {
//        Scaffold(
//            topBar = {
//                TopAppBar(
//                    title = {
//                        Row(
//                            modifier = Modifier.fillMaxWidth(),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.SpaceBetween
//                        ) {
//                            IconButton(onClick = {
//                                scope.launch { drawerState.open() }
//                            }) {
//                                Icon(Icons.Default.Menu, contentDescription = "Menú")
//                            }
//
//                            Icon(
//                                imageVector = Icons.Default.Favorite, // Icono de la app (puedes cambiarlo)
//                                contentDescription = "App Logo",
//                                modifier = Modifier.size(24.dp)
//                            )
//
//                            IconButton(onClick = {
//                                // Acción al presionar notificaciones
//                            }) {
//                                Icon(Icons.Default.Notifications, contentDescription = "Notificaciones")
//                            }
//                        }
//                    },
//                    colors = TopAppBarDefaults.topAppBarColors(
//                        containerColor = Color.Blue,
//                        titleContentColor = Color.White
//                    )
//                )
//            }
//        ) { padding ->
//            NavHost(
//                navController = navController,
//                startDestination = "login",
//                modifier = Modifier.padding(padding)
//            ) {
//                composable("login") { Login(navController) }
//                composable("home") { Home(navController) }
//                composable("register") { Register(navController) }
//                composable("patients") { PatientsView(navController) }
//                composable("patient_form") { PatientFormView(navController) }
//
//                // Rutas dummy para las demás pantallas del menú
////                navigationItems.forEach { item ->
////                    composable(item.route) {
////                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
////                            Text(text = "${item.title} Screen")
////                        }
////                    }
////                }
//            }
//        }
//    }



        NavHost(
        navController = navController,
        startDestination = "login") {
        composable("login") {
            Login(navController)
        }
        composable("home") {
            Home(navController)
        }
        composable("register") {
            Register(navController)
        }
        composable("patients") {
            PatientsView(navController)
        }
        composable("patient_form") {
            PatientFormView(navController)
        }
    }
}








data class NavigationItem(
    val icon: ImageVector,
    val title: String,
    val route: String
)