package sahraei.hamidreza.pheedly.feature.feedlist.data

import com.prof.rssparser.Channel
import com.prof.rssparser.Parser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import sahraei.hamidreza.pheedly.feature.feedlist.data.local.FeedLocalDatasource
import javax.inject.Inject

class FeedRepository @Inject constructor(
    private val feedLocalDatasource: FeedLocalDatasource,
    private val parser: Parser
) {

    fun getFeeds(): Flow<List<String>> {
        return feedLocalDatasource.getFeedUrls().map { feeds ->
            feeds.map {
                it.link
            }
        }
    }
    suspend fun getFeedChannel(url: String): Flow<Channel> {
        return flow {
            emit(parser.getChannel(url))
        }
    }
}