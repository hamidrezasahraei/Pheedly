package sahraei.hamidreza.pheedly.feature.feeds.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FeedLocalDatasource @Inject constructor() {

    private val feedUrl = listOf(
        "https://rss.art19.com/apology-line",
        "http://rss.art19.com/the-daily",
        "https://feeds.megaphone.fm/ADL9840290619"
    )

    fun getFeedUrls(): Flow<List<String>> {
        return flow {
            emit(feedUrl)
        }
    }
}