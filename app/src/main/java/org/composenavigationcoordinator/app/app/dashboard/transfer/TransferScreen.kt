package org.composenavigationcoordinator.app.app.dashboard.transfer

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
import androidx.lifecycle.viewmodel.compose.viewModel
import org.composenavigationcoordinator.app.theme.spacing

@Composable
fun TransferScreen(
    transferViewModel: TransferViewModel = viewModel(),
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Transfer ${transferViewModel.dashboardData.dashboardData}",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier.padding(top = MaterialTheme.spacing.small),
            onClick = transferViewModel::navigateToContact
        ) {
            Text(text = "To card")
        }
    }
}
