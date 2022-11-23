package sahraei.hamidreza.pheedly.feature.feeddetails.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import sahraei.hamidreza.pheedly.common.compose.CardItem
import sahraei.hamidreza.pheedly.common.compose.PheedlyProgressItem
import sahraei.hamidreza.pheedly.common.compose.ScaffoldWithTopBar
import sahraei.hamidreza.pheedly.feature.feeddetails.model.ArticleItem
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
            FeedArticlesSection(
                articles = state.articles,
                onFeedArticleClicked = onFeedArticleClicked
            )
        }
    }
}

@Composable
fun FeedArticlesSection(
    articles: List<ArticleItem>,
    onFeedArticleClicked: (String) -> Unit
) {
    ScaffoldWithTopBar(title = "Articles") {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = articles,
                key = { it.hashCode() }
            ) {
                it.title?.let { title ->
                    CardItem(
                        title = title,
                        imageUrl = it.image
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