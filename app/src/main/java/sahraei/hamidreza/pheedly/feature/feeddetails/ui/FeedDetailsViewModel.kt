package sahraei.hamidreza.pheedly.feature.feeddetails.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prof.rssparser.Article
import com.prof.rssparser.Parser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sahraei.hamidreza.pheedly.feature.feeddetails.model.ArticleItem
import sahraei.hamidreza.pheedly.feature.feeddetails.model.toArticleItems
import sahraei.hamidreza.pheedly.navigation.Screen.FeedDetail.FeedDetailArgs.FeedLink
import javax.inject.Inject

@HiltViewModel
class FeedDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val parser: Parser
) : ViewModel() {

    var state by mutableStateOf(FeedDetailsScreenState())
        private set

    private val feedLink: String? = savedStateHandle[FeedLink]

    init {
        requireNotNull(feedLink)
        getFeedDetail(feedLink)
    }

    private fun getFeedDetail(feedLink: String) {
        viewModelScope.launch {
            try {
                val channel = parser.getChannel(feedLink)
                state = state.copy(
                    isLoading = false,
                    articles = channel.toArticleItems()
                )
            } catch (generalException: Exception) {
                generalException.printStackTrace()
            }
        }
    }

}

data class FeedDetailsScreenState(
    val isLoading: Boolean = true,
    val articles: List<ArticleItem>? = null
)