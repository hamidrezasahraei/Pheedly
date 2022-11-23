package sahraei.hamidreza.pheedly.feature.feedlist.data.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sahraei.hamidreza.pheedly.feature.feedlist.data.local.dao.FeedDao
import sahraei.hamidreza.pheedly.feature.feedlist.data.local.entity.FeedEntity
import javax.inject.Inject

class FeedLocalDatasource @Inject constructor(
    private val feedDao: FeedDao
) {

    suspend fun addFeed(url: String) {
        feedDao.insert(
            FeedEntity(url)
        )
    }

    suspend fun getFeedUrls(): Flow<List<FeedEntity>> {
        return feedDao.getAll()
    }
}