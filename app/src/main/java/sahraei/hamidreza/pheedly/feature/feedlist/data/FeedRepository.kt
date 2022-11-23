package sahraei.hamidreza.pheedly.feature.feedlist.data

import com.prof.rssparser.Channel
import com.prof.rssparser.Parser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

class FeedRepository @Inject constructor(
    private val feedLocalDatasource: FeedLocalDatasource,
    private val parser: Parser
) {

    fun getFeeds(): Flow<List<String>> {
        return feedLocalDatasource.getFeedUrls()
    }
    suspend fun getFeedChannel(url: String): Flow<Channel> {
        return flow {
            emit(parser.getChannel(url))
        }
    }
}