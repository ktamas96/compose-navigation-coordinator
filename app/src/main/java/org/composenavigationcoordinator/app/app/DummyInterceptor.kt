package org.composenavigationcoordinator.app.app

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import org.composenavigationcoordinator.app.app.sla.SlaService

@Singleton
class DummyInterceptor @Inject constructor(
    private val slaService: SlaService,
) {

    fun someWork() = MutableStateFlow<String?>(null).apply {
        slaService.requestSla(
            onOtpProvided = { otp, onSuccess ->
                emit(otp)
                onSuccess.invoke()
            },
            onCancelled = {
                emit(null)
            }
        )
    }.filterNotNull()
}
