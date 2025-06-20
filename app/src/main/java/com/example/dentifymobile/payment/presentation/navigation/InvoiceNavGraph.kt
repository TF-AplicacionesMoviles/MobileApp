package com.example.dentifymobile.payment.presentation.navigation

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dentifymobile.payment.presentation.di.PresentationModule
import com.example.dentifymobile.payment.presentation.view.AddInvoiceFormView
import com.example.dentifymobile.payment.presentation.view.InvoiceView

fun NavGraphBuilder.invoiceNavGraph(navController: NavController, context: Context){
    val invoicesViewModel = PresentationModule.getInvoicesViewModel(context)
    val invoiceFormViewModel = PresentationModule.getInvoiceFormViewModel(context)

    navigation (startDestination = "payments", route = "invoices")
    {
        composable("payments") {
            InvoiceView(
                invoicesViewModel,
                toAddInvoiceForm = {
                    navController.navigate("add_invoice_form")
                }
            )

        }

        composable("add_invoice_form"){
            AddInvoiceFormView(
                invoiceFormViewModel,
                toInvoices = {
                    navController.navigate("payments")
                },
                toBack = {
                    navController.popBackStack()
                },
                onInvoicesSaved = {
                    invoicesViewModel.getAllInvoices()
                }
            )
        }
    }

}