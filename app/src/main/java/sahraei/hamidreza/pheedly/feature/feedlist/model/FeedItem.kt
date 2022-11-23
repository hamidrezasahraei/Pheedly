package sahraei.hamidreza.pheedly.feature.feedlist.model

import com.prof.rssparser.Channel

data class FeedItem(
    val title: String?,
    val link: String?,
    val image: String? = null
)

fun Channel.toFeedItem(): FeedItem {
    return FeedItem(
        title = title,
        link = link,
        image = image?.url
    )
}
