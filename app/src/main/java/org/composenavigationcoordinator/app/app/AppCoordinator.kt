package org.composenavigationcoordinator.app.app

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.composenavigationcoordinator.app.BaseCoordinator
import org.composenavigationcoordinator.app.app.card.cardCoordinator
import org.composenavigationcoordinator.app.app.dashboard.dashboardCoordinator
import org.composenavigationcoordinator.app.app.second.SecondScreen
import org.composenavigationcoordinator.app.app.sla.SlaClient
import org.composenavigationcoordinator.app.app.sla.slaCoordinator
import org.composenavigationcoordinator.app.app.start.StartScreen
import org.composenavigationcoordinator.app.app.start.StartScreenListener
import org.composenavigationcoordinator.app.coordinator
import org.composenavigationcoordinator.app.daggerViewModel
import org.composenavigationcoordinator.app.di.AppComponent
import org.composenavigationcoordinator.app.di.AppComponentFactory

class AppCoordinator(
    component: AppComponent,
) : BaseCoordinator<AppComponent>(component), StartScreenListener {

    private val appDataStore = component.appDataStore
    private val slaService = component.slaService

    var slaClient: SlaClient? = null
        private set

    init {
        appDataStore.get()
            .onEach {
                navController.navigate("dashboard")
            }.launchIn(viewModelScope)

        viewModelScope.launch {
            slaService.observe {
                slaClient = it
                navController.navigate("sla")
            }
        }
    }

    override fun onNextPressed() {
        navController.navigate("second")
    }

    override fun onCardPressed() {
        navController.navigate("card")
    }

    fun onCardFinished() {
        navController.popBackStack()
    }

    fun onSlaFinished() {
        navController.popBackStack()
        slaClient = null
    }
}

@Composable
fun AppCoordinator(
    appComponentFactory: AppComponentFactory,
) {
    val appCoordinator = coordinator {
        AppCoordinator(
            appComponentFactory.appComponent
        )
    }
    NavHost(navController = appCoordinator.navController, "start", route = "app") {
        composable("start") {
            val viewModel = daggerViewModel {
                appCoordinator.component.startViewModel.create(appCoordinator)
            }
            StartScreen(
                viewModel = viewModel,
                mainData = appCoordinator.component.mainData
            )
        }
        composable("second") {
            val secondViewModel = daggerViewModel {
                appCoordinator.component.secondViewModel
            }
            SecondScreen(secondViewModel)
        }
        dashboardCoordinator(appCoordinator.component)
        cardCoordinator(appCoordinator.component, "main", appCoordinator::onCardFinished)
        slaCoordinator(
            appCoordinator.component,
            slaClient = { appCoordinator.slaClient }
        ) {
            appCoordinator.onSlaFinished()
        }
    }
}
