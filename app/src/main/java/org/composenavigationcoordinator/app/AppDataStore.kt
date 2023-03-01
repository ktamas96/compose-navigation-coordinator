package org.composenavigationcoordinator.app

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

@Singleton
class AppDataStore @Inject constructor() {

    private val dataStateFlow = MutableStateFlow<Int?>(null)

    fun set(data: Int) {
        dataStateFlow.value = data
    }

    fun get(): Flow<Int> = dataStateFlow.filterNotNull()
}
