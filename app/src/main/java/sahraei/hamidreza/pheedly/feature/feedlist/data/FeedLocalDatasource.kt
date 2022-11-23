package sahraei.hamidreza.pheedly.feature.feedlist.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FeedLocalDatasource @Inject constructor() {

    private val feedUrl = listOf(
        "https://9gagrss.com/feed/",
        "https://icanhas.cheezburger.com/rss",
        "https://www.theonion.com/rss",
        "https://www.sadanduseless.com/feed/",
        "https://www.nasa.gov/rss/dyn/educationnews.rss",
        "https://www.nasa.gov/rss/dyn/lg_image_of_the_day.rss",
        "https://www.nasa.gov/rss/dyn/earth.rss",
    )

    fun getFeedUrls(): Flow<List<String>> {
        return flow {
            emit(feedUrl)
        }
    }
}