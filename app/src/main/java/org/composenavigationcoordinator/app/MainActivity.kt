package org.composenavigationcoordinator.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.android.material.color.DynamicColors
import org.composenavigationcoordinator.app.app.AppCoordinator
import org.composenavigationcoordinator.app.theme.ComposePlaygroundTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        DynamicColors.applyToActivityIfAvailable(this)

        // setContentView(R.layout.activity_main)
        setContent {
            ComposePlaygroundTheme {
                AppCoordinator(appComponentFactory = application as ComposeNavigationCoordinatorApplication)
            }
        }
    }
}
