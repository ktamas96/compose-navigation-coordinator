package org.composenavigationcoordinator.app.theme

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "small font",
    group = "font scales",
    fontScale = 0.5f
)
@Preview(
    name = "large font",
    group = "font scales",
    fontScale = 1.5f
)
annotation class FontScalePreviews

@Preview(
    name = "small device",
    group = "phone sizes",
    device = "spec:id=reference_phone,shape=Normal,width=206,height=446,unit=dp,dpi=420"
)
annotation class DensityPreviews

@Preview(
    name = "dark theme",
    group = "themes",
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Preview(
    name = "light theme",
    group = "themes",
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
annotation class ThemePreviews

@ThemePreviews
@FontScalePreviews
@DensityPreviews
annotation class CombinedPreviews

@Composable
inline fun ComposePlaygroundScreenPreview(
    crossinline block: @Composable () -> Unit
) {
    ComposePlaygroundPreview(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(), block)
}

@Composable
inline fun ComposePlaygroundPreview(
    modifier: Modifier = Modifier,
    crossinline block: @Composable () -> Unit
) {
    ComposePlaygroundTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = modifier
        ) { block() }
    }
}
