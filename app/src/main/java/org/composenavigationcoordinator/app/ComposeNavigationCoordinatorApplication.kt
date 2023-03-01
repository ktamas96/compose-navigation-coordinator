package org.composenavigationcoordinator.app

import android.app.Application
import org.composenavigationcoordinator.app.di.AppComponent
import org.composenavigationcoordinator.app.di.AppComponentFactory
import org.composenavigationcoordinator.app.di.DaggerAppComponent

class ComposeNavigationCoordinatorApplication : Application(), AppComponentFactory {

    override val appComponent: AppComponent = DaggerAppComponent.create()
}
