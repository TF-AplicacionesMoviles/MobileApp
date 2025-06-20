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
import com.example.dentifymobile.iam.data.remote.dto.UpdatePasswordRequest
import com.example.dentifymobile.iam.presentation.viewmodel.ProfileViewModel

@Composable
fun UpdatePasswordView(viewModel: ProfileViewModel, onSuccess: () -> Unit) {
    val currentPassword = remember { mutableStateOf("") }
    val newPassword = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val errorMessage = viewModel.errorMessage.collectAsState()

    LaunchedEffect(currentPassword.value, newPassword.value, confirmPassword.value) {
        viewModel.clearError()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Update Password", fontWeight = FontWeight.Bold, fontSize = 20.sp)

        OutlinedTextField(
            value = currentPassword.value,
            onValueChange = { currentPassword.value = it },
            label = { Text("Current Password") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = newPassword.value,
            onValueChange = { newPassword.value = it },
            label = { Text("New Password") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = confirmPassword.value,
            onValueChange = { confirmPassword.value = it },
            label = { Text("Confirm New Password") },
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMessage.value != null) {
            Text(
                text = errorMessage.value!!,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            onClick = {
                if(newPassword.value == confirmPassword.value) {
                    viewModel.updatePassword(
                        UpdatePasswordRequest(
                            oldPassword = currentPassword.value,
                            newPassword = newPassword.value
                        ),
                        onSuccess
                    )
                } else {
                    viewModel.setError("Passwords do not match.")
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Update Password")
        }
    }
}
