package sahraei.hamidreza.pheedly.feature.feeddetails.model

import com.prof.rssparser.Article
import com.prof.rssparser.Channel

data class ArticleItem(
    val title: String?,
    val link: String?,
    val image: String? = null
)

fun Channel.toArticleItems(): List<ArticleItem> {
    return articles.map { article ->
        article.toArticleItem()
    }
}

fun Article.toArticleItem(): ArticleItem {
    return ArticleItem(
        title = title,
        image = image,
        link = link
    )
}
