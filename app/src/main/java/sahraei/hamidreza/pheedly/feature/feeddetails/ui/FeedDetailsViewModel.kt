package sahraei.hamidreza.pheedly.feature.feeddetails.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prof.rssparser.Article
import com.prof.rssparser.Parser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sahraei.hamidreza.pheedly.feature.feedlist.data.FeedLocalDatasource
import javax.inject.Inject

@HiltViewModel
class FeedDetailsViewModel @Inject constructor(
    private val parser: Parser
) : ViewModel() {

    var state by mutableStateOf(FeedDetailsScreenState())
        private set

}

data class FeedDetailsScreenState(
    val isLoading: Boolean = false,
    val articles: List<Article>? = null
)