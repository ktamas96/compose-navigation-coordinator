package org.composenavigationcoordinator.app.di

import dagger.Component
import javax.inject.Singleton
import org.composenavigationcoordinator.app.AppDataStore
import org.composenavigationcoordinator.app.app.MainData
import org.composenavigationcoordinator.app.app.card.di.CardComponentFactory
import org.composenavigationcoordinator.app.app.dashboard.di.DashboardComponentFactory
import org.composenavigationcoordinator.app.app.start.StartViewModel
import org.composenavigationcoordinator.app.app.second.SecondViewModel
import org.composenavigationcoordinator.app.app.sla.SlaService
import org.composenavigationcoordinator.app.app.sla.di.SlaComponentFactory

@Component
@Singleton
interface AppComponent : DashboardComponentFactory, CardComponentFactory, SlaComponentFactory {

    val mainData: MainData
    val appDataStore: AppDataStore
    val secondViewModel: SecondViewModel
    val startViewModel: StartViewModel.Factory
    val slaService: SlaService
}
