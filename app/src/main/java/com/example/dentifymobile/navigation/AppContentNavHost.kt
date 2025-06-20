package com.example.dentifymobile.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.dentifymobile.dashboard.presentation.navigation.dashboardNavGraph
import com.example.dentifymobile.iam.presentation.navigation.profileNavGraph
import com.example.dentifymobile.inventory.items.presentation.navigation.inventoryNavGraph
import com.example.dentifymobile.patientattention.appointments.presentation.navigation.appointmentNavGraph
import com.example.dentifymobile.patientattention.patient.presentation.navigation.patientAttentionNavGraph
import com.example.dentifymobile.payment.presentation.navigation.invoiceNavGraph

@Composable
fun AppContentNavHost(navController: NavHostController) {

    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = "home",
        route = "app",
    ) {
        dashboardNavGraph(navController)
        patientAttentionNavGraph(navController, context)
        inventoryNavGraph(navController, context)
        appointmentNavGraph(navController, context)
        invoiceNavGraph(navController, context)
        profileNavGraph(navController, context)
        //agregar navgraphs
    }
}
