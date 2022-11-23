package sahraei.hamidreza.pheedly.feature.feedlist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import sahraei.hamidreza.pheedly.common.CardItem
import sahraei.hamidreza.pheedly.common.PheedlyProgressItem
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
            PheedlyProgressItem()
        }
        state.feeds != null -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = state.feeds,
                    key = { it.hashCode() }
                ) {
                    if (!it.title.isNullOrBlank()) {
                        CardItem(
                            title = it.title,
                            subtitle = it.link,
                            imageUrl = it.image
                        ) {
                            val encodedUrl = URLEncoder.encode(it.link, StandardCharsets.UTF_8.toString())
                            onFeedClicked.invoke(encodedUrl)
                        }
                    }
                }
            }
        }
    }
}