package sahraei.hamidreza.pheedly.feature.feeds.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FeedListScreen(
    feedsViewModel: FeedsViewModel = hiltViewModel()
) {
    val state = feedsViewModel.state
    when {
        state.isLoading -> {
            Box {
                CircularProgressIndicator()
            }
        }
        state.feeds != null -> {
            LazyColumn() {
                state.feeds.forEach {
                    item {
                        Text(text = it)
                    }
                }
            }
        }
    }
}