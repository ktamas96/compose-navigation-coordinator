package org.composenavigationcoordinator.app.app.dashboard.transfer

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import org.composenavigationcoordinator.app.app.dashboard.DashboardData
import org.composenavigationcoordinator.app.app.dashboard.TransferScreenListener

class TransferViewModel @AssistedInject constructor(
    val dashboardData: DashboardData,
    @Assisted private val transferScreenListener: TransferScreenListener,
) : ViewModel() {

    fun navigateToContact() {
        transferScreenListener.onContactRequested()
    }

    @AssistedFactory
    interface Factory {
        fun create(transferScreenListener: TransferScreenListener): TransferViewModel
    }
}
