package com.example.dentifymobile.app.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.dentifymobile.iam.data.storage.TokenStorage

@Composable
fun Dashboard(modifier: Modifier) {

    val context = LocalContext.current
    val accessToken = TokenStorage.getAccessToken(context)

    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("¡Bienvenido a Dentify!")
        Text(text = "Access Token: $accessToken")
        // más contenido de Home

    }


}