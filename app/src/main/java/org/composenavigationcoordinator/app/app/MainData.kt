package org.composenavigationcoordinator.app.app

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class MainData @Inject constructor() {

    val data = Random.nextInt() * 10000
}
