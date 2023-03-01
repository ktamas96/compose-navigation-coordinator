package org.composenavigationcoordinator.app.app.card.di

import dagger.BindsInstance
import dagger.Subcomponent
import org.composenavigationcoordinator.app.app.card.CardData
import org.composenavigationcoordinator.app.app.card.detail.CardDetailsViewModel
import org.composenavigationcoordinator.app.app.second.SecondViewModel

@Subcomponent
@CardScope
interface CardComponent {

    val cardDetailsViewModel: CardDetailsViewModel.Factory
    val secondViewModel: SecondViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance
            cardData: CardData
        ): CardComponent
    }
}
