package sahraei.hamidreza.pheedly.feature.feedlist.ui

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import org.mockito.kotlin.verifyBlocking
import sahraei.hamidreza.pheedly.feature.feedlist.data.FeedRepository
import sahraei.hamidreza.pheedly.rule.CoroutineTestRule

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class FeedListViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val feedRepository: FeedRepository = mock()

    private lateinit var feedLisViewModel: FeedsViewModel

    @Test
    fun `should get all feeds from repository`() = runTest {
        feedRepository.stub {
            onBlocking { getFeeds() } doReturn  flow { listOf("") }
        }
        feedLisViewModel = FeedsViewModel(
            feedRepository
        )
        verifyBlocking(feedRepository) { getFeeds() }
    }
}