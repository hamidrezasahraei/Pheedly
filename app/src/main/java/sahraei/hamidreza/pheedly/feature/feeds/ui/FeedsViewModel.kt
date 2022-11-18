package sahraei.hamidreza.pheedly.feature.feeds.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sahraei.hamidreza.pheedly.feature.feeds.data.FeedLocalDatasource
import javax.inject.Inject

@HiltViewModel
class FeedsViewModel @Inject constructor(
    private val feedLocalDatasource: FeedLocalDatasource
): ViewModel() {

    var state by mutableStateOf(FeedsScreenState())
    private set

            init {
                viewModelScope.launch {
                    feedLocalDatasource.getFeedUrls().collect {
                        state = state.copy(
                            isLoading = false,
                            feeds = it
                        )
                    }
                }
            }
}

data class FeedsScreenState(
    val isLoading: Boolean = false,
    val feeds: List<String>? = null
)