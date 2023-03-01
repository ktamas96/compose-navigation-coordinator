package org.composenavigationcoordinator.app.app.dashboard.contact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import org.composenavigationcoordinator.app.app.MainData
import org.composenavigationcoordinator.app.app.dashboard.DashboardData

@Composable
fun ContactScreen(
    dashboardData: DashboardData,
    mainData: MainData,
    contactViewModel: ContactViewModel = viewModel(),
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Contact main: ${mainData.data} dashboard: ${dashboardData.dashboardData}",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
    }
}
