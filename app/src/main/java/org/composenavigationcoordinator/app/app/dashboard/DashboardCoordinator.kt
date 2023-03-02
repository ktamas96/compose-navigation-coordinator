package org.composenavigationcoordinator.app.app.dashboard

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.composenavigationcoordinator.app.BaseCoordinator
import org.composenavigationcoordinator.app.coordinator
import org.composenavigationcoordinator.app.daggerViewModel
import org.composenavigationcoordinator.app.app.card.cardCoordinator
import org.composenavigationcoordinator.app.app.dashboard.contact.ContactScreen
import org.composenavigationcoordinator.app.app.dashboard.contact.ContactViewModel
import org.composenavigationcoordinator.app.app.dashboard.di.DashboardComponent
import org.composenavigationcoordinator.app.app.dashboard.di.DashboardComponentFactory
import org.composenavigationcoordinator.app.app.dashboard.transfer.TransferScreen

class DashboardCoordinator(
    component: DashboardComponent,
) : BaseCoordinator<DashboardComponent>(component), TransferScreenListener {

    override fun onCardRequested() {
        navController.navigate("card")
    }

    fun onCardFinished() {
        navController.popBackStack()
    }
}

interface TransferScreenListener {

    fun onCardRequested()
}

fun NavGraphBuilder.dashboardCoordinator(
    dashboardComponentFactory: DashboardComponentFactory,
) = composable("dashboard") {
    val dashboardCoordinator = coordinator {
        DashboardCoordinator(
            dashboardComponentFactory.dashboardComponent,
        )
    }

    NavHost(navController = dashboardCoordinator.navController, "transfer") {
        composable("transfer") {
            val transferViewModel = daggerViewModel {
                dashboardCoordinator.component.transferViewModel
                    .create(dashboardCoordinator)
            }
            TransferScreen(transferViewModel)
        }
        composable("contact") {
            val contactViewModel = daggerViewModel {
                ContactViewModel(nonDIDependency = "Non DI dependency")
            }
            ContactScreen(contactViewModel)
        }
        cardCoordinator(
            dashboardCoordinator.component,
            "dashboard",
            dashboardCoordinator::onCardFinished
        )
    }
}
