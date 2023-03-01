package org.composenavigationcoordinator.app.app.sla

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

interface SlaScreenListener {
    fun onOtpProvided(otp: String)
    fun onCancelled()
}

class SlaViewModel
@AssistedInject constructor(
    @Assisted
    screenListener: SlaScreenListener,
) {

    @AssistedFactory
    interface Factory {
        fun create(screenListener: SlaScreenListener): SlaViewModel
    }

    val otpProvided = screenListener::onOtpProvided
    val cancel = screenListener::onCancelled
}
