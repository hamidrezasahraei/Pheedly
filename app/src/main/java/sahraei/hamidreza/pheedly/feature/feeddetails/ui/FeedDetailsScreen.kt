package sahraei.hamidreza.pheedly.feature.feeddetails.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FeedListScreen(
    feedsViewModel: FeedDetailsViewModel = hiltViewModel()
) {
    val state = feedsViewModel.state
    when {
        state.isLoading -> {
            Box {
                CircularProgressIndicator()
            }
        }
        state.articles != null -> {
            LazyColumn {
                state.articles.forEach {
                    it.title?.let { title ->
                        item {
                            Text(text = title)
                        }
                    }
                }
            }
        }
    }
}