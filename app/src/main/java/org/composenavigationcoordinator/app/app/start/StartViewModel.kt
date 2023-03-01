package org.composenavigationcoordinator.app.app.start

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlin.random.Random
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface StartScreenListener {

    fun onNextPressed()

    fun onCardPressed()
}

class StartViewModel @AssistedInject constructor(
    @Assisted
    private val screenListener: StartScreenListener,
) : ViewModel() {
    private val random = Random(System.currentTimeMillis())
    private val _state = MutableStateFlow<MainState>(MainState.Welcome)
    val state = _state.asStateFlow()

    fun next() {
        screenListener.onNextPressed()
    }

    fun card() {
        screenListener.onCardPressed()
    }

    @AssistedFactory
    interface Factory {
        fun create(screenListener: StartScreenListener): StartViewModel
    }
}

sealed interface MainState {

    object Welcome : MainState
    object Loading : MainState
    data class Items(val items: List<Item>) : MainState
}

data class Item(val color: Long, val title: String)
