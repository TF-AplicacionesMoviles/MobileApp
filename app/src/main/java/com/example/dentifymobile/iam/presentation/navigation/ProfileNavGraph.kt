package com.example.dentifymobile.iam.presentation.navigation

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dentifymobile.iam.presentation.di.PresentationModule
import com.example.dentifymobile.iam.presentation.view.ProfileView
import com.example.dentifymobile.iam.presentation.view.UpdateInformationView
import com.example.dentifymobile.iam.presentation.view.UpdatePasswordView

fun NavGraphBuilder.profileNavGraph(navController: NavController, context: Context) {
    val profileViewModel = PresentationModule.getProfileViewModel(context)

    navigation (startDestination = "profile", route = "profileInfo"){
        composable("profile"){
            ProfileView(
                viewModel = profileViewModel,
                toUpdateInformation = { navController.navigate("update_info") },
                toUpdatePassword = { navController.navigate("update_password") }
            )
        }

        composable("update_info") {
            UpdateInformationView(viewModel = profileViewModel) {
                navController.popBackStack()
            }
        }

        composable("update_password") {
            UpdatePasswordView(viewModel = profileViewModel) {
                navController.popBackStack()
            }
        }


    }
}