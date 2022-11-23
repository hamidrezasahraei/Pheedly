package sahraei.hamidreza.pheedly.feature.feedlist.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import sahraei.hamidreza.pheedly.common.compose.CardItem
import sahraei.hamidreza.pheedly.common.compose.PheedlyProgressItem
import sahraei.hamidreza.pheedly.common.compose.ScaffoldWithTopBar
import sahraei.hamidreza.pheedly.feature.feedlist.model.FeedItem
import sahraei.hamidreza.pheedly.ui.theme.LightGreys90
import sahraei.hamidreza.pheedly.ui.theme.LightPheedly
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
            FeedListSection(
                feeds = state.feeds,
                onFeedClicked = onFeedClicked
            )
        }
    }
}

@Composable
fun FeedListSection(
    feeds: List<FeedItem>,
    onFeedClicked: (feedLink: String) -> Unit
) {
    ScaffoldWithTopBar(title = "Feeds") {
        Box {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = feeds,
                    key = { it.hashCode() }
                ) {
                    if (!it.title.isNullOrBlank()) {
                        CardItem(
                            title = it.title,
                            subtitle = it.subtitle,
                            imageUrl = it.image
                        ) {
                            val encodedUrl = URLEncoder.encode(it.link, StandardCharsets.UTF_8.toString())
                            onFeedClicked.invoke(encodedUrl)
                        }
                    }
                }
            }

            FloatingActionButton(
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
fun FloatingActionButton(
    modifier: Modifier = Modifier
) {
    val ctx = LocalContext.current
    FloatingActionButton(
        onClick = {
            Toast.makeText(ctx, "Simple Floating Action Button", Toast.LENGTH_SHORT).show()
        },
        backgroundColor = LightPheedly,
        contentColor = LightGreys90,
        modifier = modifier
            .padding(16.dp)
    ) {
        Icon(Icons.Filled.Add, "")
    }
}

@Preview
@Composable
fun FeedListPreview(){
    FeedListSection(
        feeds = listOf(
            FeedItem(
                title = "Sample Title",
                subtitle = "This is a sample Subtitle",
                link = "https://google.com"
            )
        ),
        onFeedClicked = { println("On Feed Clicked!") }
    )
}