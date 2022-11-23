package sahraei.hamidreza.pheedly.feature.feedlist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sahraei.hamidreza.pheedly.feature.feedlist.data.FeedRepository
import sahraei.hamidreza.pheedly.feature.feedlist.model.FeedItem
import sahraei.hamidreza.pheedly.feature.feedlist.model.toFeedItem
import javax.inject.Inject

@HiltViewModel
class FeedsViewModel @Inject constructor(
    private val feedRepository: FeedRepository
) : ViewModel() {

    var state by mutableStateOf(FeedsScreenState())
        private set

    init {
        getFeeds()
    }

    private fun getFeeds() {
        viewModelScope.launch {
            feedRepository.getFeeds().collect { urls ->
                val items = mutableListOf<FeedItem>()
                urls.forEach { url ->
                    val job = launch {
                        feedRepository.getFeedChannel(url).collect { channel ->
                            items.add(
                                channel.toFeedItem(url)
                            )
                        }
                    }
                    job.join()
                }
                state = state.copy(
                    isLoading = false,
                    feeds = items
                )
            }
        }
    }
}

data class FeedsScreenState(
    val isLoading: Boolean = true,
    val feeds: List<FeedItem>? = null
)