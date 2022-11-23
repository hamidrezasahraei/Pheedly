package sahraei.hamidreza.pheedly.feature.feedlist.data.local.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import sahraei.hamidreza.pheedly.feature.feedlist.data.local.dao.FeedDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFeedDatabase(
        @ApplicationContext context: Context
    ): FeedDatabase {
        return Room.databaseBuilder(
            context,
            FeedDatabase::class.java,
            "Pheedly.db"
        )
            .createFromAsset("database/feeds.db")
            .build()
    }

    @Provides
    fun provideFeedDao(
        feedDatabase: FeedDatabase
    ): FeedDao {
        return feedDatabase.feedDao()
    }
}