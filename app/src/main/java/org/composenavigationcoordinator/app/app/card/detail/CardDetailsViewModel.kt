package org.composenavigationcoordinator.app.app.card.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.composenavigationcoordinator.app.app.DummyInterceptor
import org.composenavigationcoordinator.app.app.card.CardData

interface CardDetailsScreenListener {
    fun onFinished()
    fun onSecondRequested()
}

class CardDetailsViewModel @AssistedInject constructor(
    cardData: CardData,
    private val dummyInterceptor: DummyInterceptor,
    @Assisted
    private val screenListener: CardDetailsScreenListener,
) : ViewModel() {

    val parent = cardData.parent

    fun slaOperation() {
        dummyInterceptor.someWork()
            .onEach {
                delay(2000)
                Log.i("SLA", "Otp provided $it")
                screenListener.onFinished()
            }.launchIn(viewModelScope)
    }

    val onSecondRequested = screenListener::onSecondRequested

    @AssistedFactory
    interface Factory {

        fun create(screenListener: CardDetailsScreenListener): CardDetailsViewModel
    }
}
