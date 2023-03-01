package org.composenavigationcoordinator.app.element

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random
import kotlinx.coroutines.launch
import org.composenavigationcoordinator.app.theme.CombinedPreviews
import org.composenavigationcoordinator.app.theme.ComposePlaygroundPreview
import org.composenavigationcoordinator.app.theme.spacing

@Composable
fun ItemRow(modifier: Modifier = Modifier, color: Long, title: String) {
    val animatedColor = remember {
        Animatable(Color(color))
    }
    val scope = rememberCoroutineScope()
    Row(
        modifier = modifier.clickable {
            scope.launch {
                animatedColor.animateTo(Color(Random.nextLong(0xFFFFFFFF)), tween(5000))
            }
        },
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(animatedColor.value)
                .size(80.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@CombinedPreviews
@Composable
fun ItemRowPreview() {
    ComposePlaygroundPreview {
        ItemRow(color = 0xFFAABBCC, title = "Preview Item")
    }
}
