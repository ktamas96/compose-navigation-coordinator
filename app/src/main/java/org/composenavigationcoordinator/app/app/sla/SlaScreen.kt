package org.composenavigationcoordinator.app.app.sla

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.composenavigationcoordinator.app.theme.spacing

@Composable
fun SlaScreen(
    viewModel: SlaViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "SLA Screen",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier.padding(top = MaterialTheme.spacing.small),
            onClick = {
                viewModel.otpProvided("234234")
            }
        ) {
            Text(text = "Provide OTP")
        }
        Button(
            modifier = Modifier.padding(top = MaterialTheme.spacing.small),
            onClick = viewModel.cancel
        ) {
            Text(text = "Cancel")
        }
    }
}
