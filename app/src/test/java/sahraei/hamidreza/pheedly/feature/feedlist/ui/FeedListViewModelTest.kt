package sahraei.hamidreza.pheedly.feature.feedlist.ui

import com.prof.rssparser.Channel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyBlocking
import org.mockito.kotlin.whenever
import sahraei.hamidreza.pheedly.feature.feedlist.data.FeedRepository
import sahraei.hamidreza.pheedly.rule.CoroutineTestRule
import sahraei.hamidreza.pheedly.rule.testScheduler
import sahraei.hamidreza.pheedly.utils.runStandardTest

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class FeedListViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val feedRepository: FeedRepository = mock()

    private lateinit var feedLisViewModel: FeedsViewModel

    @Test
    fun `should call getFeeds from repository after viewModel creating instance`() = runTest {
        feedRepository.stub {
            onBlocking { getFeeds() } doReturn flow { listOf("") }
        }
        feedLisViewModel = FeedsViewModel(
            feedRepository
        )
        verifyBlocking(feedRepository) { getFeeds() }
    }

    @Test
    fun `should call getFeedChannel from repository when feed exists in database`() = runTest {
        val url = "url"
        val channel = Channel(
            null,
            null,
            null,
            null,
            null,
            null,
            articles = listOf(),
            null
        )
        whenever(feedRepository.getFeeds()) doReturn flowOf(listOf(url))
        whenever(feedRepository.getFeedChannel(url)) doReturn flowOf(channel)
        feedLisViewModel = FeedsViewModel(
            feedRepository
        )
        verify(feedRepository).getFeedChannel(url)
    }

    @Test
    fun `should call getFeedChannel same number as items count in database`() = runTest {
        val url = "url"
        val channel = Channel(
            null,
            null,
            null,
            null,
            null,
            null,
            articles = listOf(),
            null
        )
        whenever(feedRepository.getFeeds()) doReturn flowOf(listOf(url, url, url))
        whenever(feedRepository.getFeedChannel(url)) doReturn flowOf(channel)
        feedLisViewModel = FeedsViewModel(
            feedRepository
        )
        verify(feedRepository, times(3)).getFeedChannel(url)
    }

    @Test
    fun `should show loading state when viewmodel initiated`() = runStandardTest {
        whenever(feedRepository.getFeeds()) doReturn flowOf(listOf(""))
        whenever(feedRepository.getFeedChannel("")) doReturn flowOf(mock())
        feedLisViewModel = FeedsViewModel(
            feedRepository
        )
        assertTrue(feedLisViewModel.state.isLoading)
    }

    @Test
    fun `should dismiss loading state when data loaded`() = runTest {
        whenever(feedRepository.getFeeds()) doReturn flowOf(listOf(""))
        whenever(feedRepository.getFeedChannel("")) doReturn flowOf(mock())
        feedLisViewModel = FeedsViewModel(
            feedRepository
        )
        assertFalse(feedLisViewModel.state.isLoading)
    }
}