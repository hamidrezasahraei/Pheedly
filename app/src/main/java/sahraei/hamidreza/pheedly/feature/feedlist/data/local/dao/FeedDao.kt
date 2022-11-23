package sahraei.hamidreza.pheedly.feature.feedlist.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import sahraei.hamidreza.pheedly.feature.feedlist.data.local.entity.FeedEntity

@Dao
interface FeedDao {

    @Query("SELECT * FROM feed")
    fun getAll(): Flow<List<FeedEntity>>

    @Insert
    suspend fun insert(feedEntity: FeedEntity)
}