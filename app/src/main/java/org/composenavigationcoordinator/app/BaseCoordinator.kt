package org.composenavigationcoordinator.app

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

abstract class BaseCoordinator<C>(
    val component: C
) : ViewModel() {

    lateinit var navController: NavHostController
}
