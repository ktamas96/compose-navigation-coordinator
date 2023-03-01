package org.composenavigationcoordinator.app.app.sla

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch
import org.composenavigationcoordinator.app.BaseCoordinator
import org.composenavigationcoordinator.app.coordinator
import org.composenavigationcoordinator.app.app.sla.di.SlaComponent
import org.composenavigationcoordinator.app.app.sla.di.SlaComponentFactory

data class SlaClient(
    val onOtpProvided: suspend (String, () -> Unit) -> Unit,
    val onCancelled: suspend () -> Unit
)

class SlaCoordinator(
    component: SlaComponent,
    private val slaClient: SlaClient,
    private val onBack: () -> Unit
) : BaseCoordinator<SlaComponent>(component), SlaScreenListener {

    override fun onOtpProvided(otp: String) {
        viewModelScope.launch {
            slaClient.onOtpProvided(otp) {
                onBack.invoke()
            }
        }
    }

    override fun onCancelled() {
        viewModelScope.launch {
            slaClient.onCancelled()
        }
    }
}

fun NavGraphBuilder.slaCoordinator(
    slaComponentFactory: SlaComponentFactory,
    slaClient: () -> SlaClient?,
    onBack: () -> Unit,
) = composable("sla") {
    val coordinator = coordinator {
        SlaCoordinator(
            component = slaComponentFactory.slaComponent,
            slaClient() ?: error("No SlaClient provided"),
            onBack
        )
    }
    NavHost(
        navController = coordinator.navController,
        startDestination = "slascreen",
        route = "sla"
    ) {
        composable("slascreen") {
            val viewModel = coordinator.component.slaViewModel.create(coordinator)
            SlaScreen(viewModel = viewModel)
        }
    }
}
