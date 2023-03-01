package org.composenavigationcoordinator.app.app.dashboard

import javax.inject.Inject
import kotlin.random.Random
import org.composenavigationcoordinator.app.app.dashboard.di.DashboardScope

@DashboardScope
class DashboardData @Inject constructor() {

    val dashboardData = "Dashboard: ${Random.nextInt() * 1000}"
}
