package sahraei.hamidreza.pheedly.feature.feedlist.data.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sahraei.hamidreza.pheedly.feature.feedlist.data.local.dao.FeedDao
import sahraei.hamidreza.pheedly.feature.feedlist.data.local.entity.FeedEntity
import javax.inject.Inject

class FeedLocalDatasource @Inject constructor(
    private val feedDao: FeedDao
) {

    fun getFeedUrls(): Flow<List<FeedEntity>> {
        return feedDao.getAll()
    }
}