package sahraei.hamidreza.pheedly.feature.feedlist.model

import com.prof.rssparser.Channel

data class FeedItem(
    val title: String?,
    val subtitle: String?,
    val link: String?,
    val image: String? = null
)

fun Channel.toFeedItem(url: String): FeedItem {
    return FeedItem(
        title = title,
        subtitle = link,
        link = url,
        image = image?.url
    )
}
