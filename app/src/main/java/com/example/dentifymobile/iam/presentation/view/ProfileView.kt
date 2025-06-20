package com.example.dentifymobile.iam.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dentifymobile.iam.presentation.viewmodel.ProfileViewModel

@Composable
fun ProfileView(
    viewModel: ProfileViewModel,
    toUpdateInformation: () -> Unit,
    toUpdatePassword: () -> Unit
) {
    val userInfo = viewModel.userInfo.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchUserInfo()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Profile Information",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (errorMessage.value != null) {
            Text(
                text = "Error: ${errorMessage.value}",
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        } else if (userInfo.value != null) {
            val user = userInfo.value!!

            ProfileField(label = "Company Name", value = user.companyName)
            ProfileField(label = "Email", value = user.email)
            ProfileField(label = "Full Name", value = user.fullName)
            ProfileField(label = "Username", value = user.username)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = toUpdateInformation,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text("Update Information")
            }

            Button(
                onClick = toUpdatePassword,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text("Change Password")
            }

        } else {
            Text("Loading...", fontSize = 16.sp)
        }
    }
}

@Composable
private fun ProfileField(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color.Gray)
        Text(text = value, fontSize = 16.sp)
    }
}