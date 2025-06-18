package com.example.dentifymobile.payment.presentation.navigation

import android.content.Context
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.NavHostController
import com.example.dentifymobile.payment.presentation.di.PresentationModule
import com.example.dentifymobile.payment.presentation.viewmodel.FactureViewModel
import com.example.dentifymobile.payment.presentation.viewmodel.PaymentViewModel
import com.example.dentifymobile.payment.ui.screens.FacturesScreen
import com.example.dentifymobile.payment.ui.screens.PaymentScreen
import com.example.dentifymobile.payment.ui.screens.PaymentSuccessScreen
import com.example.dentifymobile.payment.ui.screens.RegisterPaymentScreen
import com.example.dentifymobile.payment.ui.screens.DetailFacturaScreen

fun NavGraphBuilder.paymentNavGraph(navController: NavHostController, context: Context) {
    val paymentViewModel = PresentationModule.getPaymentViewModel(context)
    val factureViewModel = PresentationModule.getFactureViewModel(context)

    navigation(startDestination = "payment_screen", route = "payment") {
        composable("payment_screen") {
            PaymentScreen(viewModel = paymentViewModel, navController = navController)
        }

        composable("register_payment_screen") {
            RegisterPaymentScreen(navController = navController)
        }

        composable("paymentSuccessScreen") {
            PaymentSuccessScreen(navController = navController)
        }

        composable("factures_screen") {
            FacturesScreen(navController = navController, factureViewModel = factureViewModel)
        }

        composable("invoice_detail_screen/{appointmentId}") { backStackEntry ->
            val factureId = backStackEntry.arguments?.getString("appointmentId") ?: ""
            DetailFacturaScreen(
                navController = navController,
                factureId = factureId,
                factureViewModel = factureViewModel
            )
        }
    }
}

