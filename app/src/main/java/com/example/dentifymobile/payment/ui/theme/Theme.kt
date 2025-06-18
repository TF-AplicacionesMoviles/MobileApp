// ui/theme/Theme.kt
package com.example.dentifymobile.payment.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.example.dentifymobile.payment.ui.theme.Typography

@Composable
fun PaymentAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = PrimaryColor,
            secondary = SecondaryColor,
            background = BackgroundColor,
            onPrimary = OnPrimaryColor,
            onBackground = OnBackgroundColor
        ),
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
