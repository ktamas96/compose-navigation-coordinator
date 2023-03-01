package org.composenavigationcoordinator.app.app.card

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.composenavigationcoordinator.app.BaseCoordinator
import org.composenavigationcoordinator.app.app.card.detail.CardDetailsScreen
import org.composenavigationcoordinator.app.app.card.detail.CardDetailsScreenListener
import org.composenavigationcoordinator.app.app.card.di.CardComponent
import org.composenavigationcoordinator.app.app.card.di.CardComponentFactory
import org.composenavigationcoordinator.app.app.second.SecondScreen
import org.composenavigationcoordinator.app.coordinator
import org.composenavigationcoordinator.app.daggerViewModel

class CardCoordinator(
    cardComponent: CardComponent,
    private val onFinished: () -> Unit,
) : BaseCoordinator<CardComponent>(cardComponent), CardDetailsScreenListener {

    override fun onFinished() {
        this.onFinished.invoke()
    }

    override fun onSecondRequested() {
        navController.navigate("second")
    }
}

fun NavGraphBuilder.cardCoordinator(
    cardComponentFactory: CardComponentFactory,
    parent: String,
    onFinished: () -> Unit
) = composable("card") {
    val coordinator = coordinator {
        CardCoordinator(
            cardComponentFactory
                .cardComponent.create(CardData(parent)),
            onFinished
        )
    }
    NavHost(navController = coordinator.navController, "details") {
        composable("details") {
            val cardDetailsViewModel = daggerViewModel {
                coordinator.component.cardDetailsViewModel.create(coordinator)
            }
            CardDetailsScreen(viewModel = cardDetailsViewModel)
        }
        composable("second") {
            val secondViewModel = daggerViewModel {
                coordinator.component.secondViewModel
            }
            SecondScreen(viewModel = secondViewModel)
        }
    }
}
