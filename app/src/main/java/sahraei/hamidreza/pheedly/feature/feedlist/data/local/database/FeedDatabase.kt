package sahraei.hamidreza.pheedly.feature.feedlist.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import sahraei.hamidreza.pheedly.feature.feedlist.data.local.dao.FeedDao
import sahraei.hamidreza.pheedly.feature.feedlist.data.local.entity.FeedEntity

@Database(entities = [FeedEntity::class], version = 1)
abstract class FeedDatabase : RoomDatabase() {
    abstract fun feedDao(): FeedDao
}