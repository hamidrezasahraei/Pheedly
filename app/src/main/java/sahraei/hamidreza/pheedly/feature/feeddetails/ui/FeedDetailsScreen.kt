package sahraei.hamidreza.pheedly.feature.feeddetails.ui

import androidx.compose.foundation.clickable
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
fun FeedDetailsScreen(
    feedsViewModel: FeedDetailsViewModel = hiltViewModel(),
    onFeedArticleClicked: (String) -> Unit
) {
    val state = feedsViewModel.state
    when {
        state.isLoading -> {
            PheedlyProgressItem()
        }
        state.articles != null -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = state.articles,
                    key = { it }
                ) {
                    it.title?.let { title ->
                        CardItem(
                            title = title
                        ) {
                            it.link?.let { link ->
                                val encodedUrl = URLEncoder.encode(link, StandardCharsets.UTF_8.toString())
                                onFeedArticleClicked.invoke(encodedUrl)
                            }
                        }
                    }
                }
            }
        }
    }
}