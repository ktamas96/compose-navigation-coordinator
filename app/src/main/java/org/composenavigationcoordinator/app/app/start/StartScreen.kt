package org.composenavigationcoordinator.app.app.start

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.composenavigationcoordinator.app.element.ItemRow
import org.composenavigationcoordinator.app.app.MainData
import org.composenavigationcoordinator.app.theme.CombinedPreviews
import org.composenavigationcoordinator.app.theme.ComposePlaygroundScreenPreview
import org.composenavigationcoordinator.app.theme.spacing

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun StartScreen(
    viewModel: StartViewModel,
    mainData: MainData,
) {
    val mainState by viewModel.state.collectAsStateWithLifecycle()
    StartScreen(mainState, mainData, viewModel::next, viewModel::card)
}

@SuppressLint("SetTextI18n")
@Composable
fun StartScreen(
    state: MainState,
    mainData: MainData,
    onNext: () -> Unit,
    onCard: () -> Unit
) {
    when (state) {
        is MainState.Items -> {
            LazyColumn(
                modifier = Modifier.padding(MaterialTheme.spacing.small),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
            ) {
                items(state.items, { it.title }) { item ->
                    ItemRow(
                        modifier = Modifier.fillMaxWidth(),
                        color = item.color,
                        title = item.title
                    )
                }
            }
        }

        MainState.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        MainState.Welcome -> Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Hello Compose ${mainData.data}",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
            AndroidView(
                factory = { context ->
                    TextView(context).apply {
                        text = "XML TextView"
                    }
                }
            )
            Button(
                modifier = Modifier.padding(top = MaterialTheme.spacing.small),
                onClick = onNext
            ) {
                Text(text = "Next")
            }
            Button(
                modifier = Modifier.padding(top = MaterialTheme.spacing.small),
                onClick = onCard
            ) {
                Text(text = "Card")
            }
        }
    }
}

@CombinedPreviews
@Composable
fun MainScreenPreview() {
    ComposePlaygroundScreenPreview {
        StartScreen(MainState.Welcome, mainData = MainData(), {}, {})
    }
}

@CombinedPreviews
@Composable
fun MainScreenLoading() {
    ComposePlaygroundScreenPreview {
        StartScreen(MainState.Loading, mainData = MainData(), {}, {})
    }
}

@CombinedPreviews
@Composable
fun MainScreenContent() {
    ComposePlaygroundScreenPreview {
        StartScreen(
            MainState.Items(
                listOf(
                    Item(0xFFAABBCC, "Item 1"),
                    Item(0xFFBBAACC, "Item 2"),
                    Item(0xFFBBCCAA, "Item 3"),
                )
            ), MainData(), {}, {}
        )
    }
}
