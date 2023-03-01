package org.composenavigationcoordinator.app.app.dashboard.di

import dagger.Subcomponent
import org.composenavigationcoordinator.app.app.MainData
import org.composenavigationcoordinator.app.app.card.di.CardComponentFactory
import org.composenavigationcoordinator.app.app.dashboard.DashboardData
import org.composenavigationcoordinator.app.app.dashboard.transfer.TransferViewModel

@Subcomponent
@DashboardScope
interface DashboardComponent : CardComponentFactory {

    val transferViewModel: TransferViewModel.Factory

    val dashboardData: DashboardData
    val mainData: MainData
}
