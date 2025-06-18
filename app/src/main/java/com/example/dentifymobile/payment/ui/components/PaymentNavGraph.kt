package com.example.dentifymobile.payment.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.dentifymobile.payment.ui.screens.RegisterPaymentScreen
import androidx.navigation.compose.composable
import com.example.dentifymobile.payment.ui.screens.PaymentSuccessScreen

@Composable
fun PaymentNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "registerPaymentScreen"
    ) {
        composable("registerPaymentScreen") {
            RegisterPaymentScreen(navController = navController)
        }
        composable("paymentSuccessScreen") {
            PaymentSuccessScreen(navController = navController)
        }
    }
}
