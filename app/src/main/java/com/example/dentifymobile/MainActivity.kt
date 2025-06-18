package com.example.dentifymobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dentifymobile.navigation.DentifyApp
import com.example.dentifymobile.ui.theme.DentifyMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DentifyMobileTheme {
                DentifyApp()
            }
        }
    }
}

