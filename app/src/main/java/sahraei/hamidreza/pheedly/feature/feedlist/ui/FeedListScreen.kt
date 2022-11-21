package sahraei.hamidreza.pheedly.feature.feedlist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun FeedListScreen(
    feedsViewModel: FeedsViewModel = hiltViewModel(),
    onFeedClicked: (feedLink: String) -> Unit,
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
                        Text(
                            text = it,
                            modifier = Modifier
                                .clickable {
                                    val encodedUrl = URLEncoder.encode(it, StandardCharsets.UTF_8.toString())
                                    onFeedClicked.invoke(encodedUrl)
                                }
                        )
                    }
                }
            }
        }
    }
}