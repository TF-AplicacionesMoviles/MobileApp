package com.example.dentifymobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.dentifymobile.iam.presentation.navigation.MainScreen
import com.example.dentifymobile.ui.theme.DentifyMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DentifyMobileTheme {
//                DentifyApp()
//                MyApp()
//                val navController = rememberNavController()
                MainScreen()
            }
        }
    }
}


//@Composable
//fun MyApp() {
//    val navController = rememberNavController() // Aquí se crea
//    RootNavGraph(navController = navController) // Aquí se pasa
//}