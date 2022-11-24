package sahraei.hamidreza.pheedly.feature.feedlist.data

import com.prof.rssparser.Parser
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyBlocking
import sahraei.hamidreza.pheedly.feature.feedlist.data.local.FeedLocalDatasource


@RunWith(MockitoJUnitRunner::class)
class FeedRepositoryTest {

    private val feedLocalDatasource: FeedLocalDatasource = mock()

    @Mock
    private lateinit var parser: Parser

    private lateinit var feedRepository: FeedRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        feedRepository = FeedRepository(
            feedLocalDatasource,
            parser
        )
    }

    @Test
    fun `should return feed urls from localdatasource`() {
        runBlocking { feedRepository.getFeeds() }
        verifyBlocking(feedLocalDatasource) { getFeedUrls() }
    }

    @Test
    fun `should return feed channel from url`() {
        val url = "https://google.com"
        runBlocking { feedRepository.getFeedChannel(url) }
        verifyBlocking(parser) { getChannel(url) }
    }

    @Test
    fun `should add channel by url`() {
        val url = "https://google.com"
        runBlocking { feedRepository.addFeed(url) }
        verifyBlocking(feedLocalDatasource) { addFeed(url) }
    }
}