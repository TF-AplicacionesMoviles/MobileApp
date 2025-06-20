package com.example.dentifymobile.iam.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dentifymobile.iam.data.remote.dto.UpdateInformationRequest
import com.example.dentifymobile.iam.presentation.viewmodel.ProfileViewModel

@Composable
fun UpdateInformationView(viewModel: ProfileViewModel, onSuccess: () -> Unit) {
    val userInfo = viewModel.userInfo.collectAsState()
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val companyName = remember { mutableStateOf("") }
    val errorMessage = viewModel.errorMessage.collectAsState()

    LaunchedEffect(userInfo.value) {
        userInfo.value?.let {
            val parts = it.fullName.split(" ", limit = 2)
            firstName.value = parts.getOrNull(0) ?: ""
            lastName.value = parts.getOrNull(1) ?: ""
            email.value = it.email
            companyName.value = it.companyName
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Update Profile Info", fontWeight = FontWeight.Bold, fontSize = 20.sp)

        OutlinedTextField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = lastName.value,
            onValueChange = { lastName.value = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = companyName.value,
            onValueChange = { companyName.value = it },
            label = { Text("Company Name") },
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMessage.value != null) {
            Text("Error: ${errorMessage.value}", color = Color.Red)
        }

        Button(
            onClick = {
                viewModel.updateInformation(
                    UpdateInformationRequest(
                        firstName = firstName.value,
                        lastName = lastName.value,
                        email = email.value,
                        companyName = companyName.value

                    ),
                    onSuccess
                )
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Update Info")
        }
    }
}
