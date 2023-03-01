package org.composenavigationcoordinator.app.app.second

import androidx.lifecycle.ViewModel
import javax.inject.Inject
import kotlin.random.Random
import org.composenavigationcoordinator.app.AppDataStore

class SecondViewModel @Inject constructor(
    private val appDataStore: AppDataStore,
) : ViewModel() {

    fun onButtonClicked() {
        appDataStore.set(Random.nextInt())
    }
}
