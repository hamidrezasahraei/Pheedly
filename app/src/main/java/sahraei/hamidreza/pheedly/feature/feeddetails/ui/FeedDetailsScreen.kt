package sahraei.hamidreza.pheedly.feature.feeddetails.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import sahraei.hamidreza.pheedly.common.PheedlyProgressItem

@Composable
fun FeedDetailsScreen(
    feedsViewModel: FeedDetailsViewModel = hiltViewModel()
) {
    val state = feedsViewModel.state
    when {
        state.isLoading -> {
            PheedlyProgressItem()
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