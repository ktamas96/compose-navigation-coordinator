package org.composenavigationcoordinator.app.view

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.AbstractComposeView
import org.composenavigationcoordinator.app.R
import org.composenavigationcoordinator.app.element.ItemRow
import org.composenavigationcoordinator.app.theme.ComposePlaygroundTheme

class ItemRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

    private var color by mutableStateOf(0L)
    private var title by mutableStateOf("")

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ItemRowView,
            0, 0
        ).apply {
            try {
                color = getString(R.styleable.ItemRowView_itemColor)?.toLongOrNull(16) ?: 0
                title = getString(R.styleable.ItemRowView_itemTitle) ?: ""
            } finally {
                recycle()
            }
        }
    }

    @Composable
    override fun Content() {
        ComposePlaygroundTheme {
            ItemRow(color = color, title = title)
        }
    }
}
