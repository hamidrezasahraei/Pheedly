package sahraei.hamidreza.pheedly.feature.feedlist.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feed")
data class FeedEntity(
    @PrimaryKey val link: String
)