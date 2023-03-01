package org.composenavigationcoordinator.app.app.sla

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

@Singleton
class SlaService @Inject constructor() {

    private val slaStateFlow = MutableStateFlow<SlaClient?>(null)

    fun requestSla(
        onOtpProvided: suspend (String, () -> Unit) -> Unit,
        onCancelled: suspend () -> Unit
    ) {
        slaStateFlow.value = SlaClient(onOtpProvided, onCancelled)
    }

    suspend fun observe(block: (SlaClient) -> Unit) {
        slaStateFlow.filterNotNull()
            .collect {
                block(it)
            }
    }
}
