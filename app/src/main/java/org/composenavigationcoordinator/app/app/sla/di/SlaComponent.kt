package org.composenavigationcoordinator.app.app.sla.di

import dagger.Subcomponent
import org.composenavigationcoordinator.app.app.card.di.CardComponentFactory
import org.composenavigationcoordinator.app.app.sla.SlaViewModel

@SlaScope
@Subcomponent
interface SlaComponent: CardComponentFactory {

    val slaViewModel: SlaViewModel.Factory
}
