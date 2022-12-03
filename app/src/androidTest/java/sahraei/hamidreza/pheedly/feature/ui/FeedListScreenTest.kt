package sahraei.hamidreza.pheedly.feature.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runAndroidComposeUiTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import sahraei.hamidreza.pheedly.MainActivity
import sahraei.hamidreza.pheedly.common.compose.AddFeedDialogTag
import sahraei.hamidreza.pheedly.common.compose.AddFeedTextFieldTag
import sahraei.hamidreza.pheedly.common.compose.ProgressBarTag
import sahraei.hamidreza.pheedly.feature.feedlist.ui.AddFeedFab

@HiltAndroidTest
class FeedListScreenTest {

    @get:Rule
    var hiltTestRule = HiltAndroidRule(this)

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun shouldShowProgressBarFirst() = runAndroidComposeUiTest<MainActivity>{
        onNodeWithTag(ProgressBarTag).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun showAddFeedDialogByClickOnFab() = runAndroidComposeUiTest<MainActivity>{
        waitUntil {
            onAllNodesWithTag(AddFeedFab).fetchSemanticsNodes().size == 1
        }
        onNodeWithTag(AddFeedFab).performClick()
        onNodeWithTag(AddFeedDialogTag).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun canAddFeed() = runAndroidComposeUiTest<MainActivity>{
        val feedUrl = "https://www.varzesh3.com/rss/all"
        waitUntil {
            onAllNodesWithTag(AddFeedFab).fetchSemanticsNodes().size == 1
        }
        onNodeWithTag(AddFeedFab).performClick()
        onNodeWithTag(AddFeedDialogTag).assertIsDisplayed()
        onNodeWithTag(AddFeedTextFieldTag).performTextInput(feedUrl)
        onNodeWithText("Add").performClick()
        waitUntil(
            timeoutMillis = 5000
        ) {
            onAllNodesWithText(feedUrl).fetchSemanticsNodes().size == 1
        }

        onNodeWithText(feedUrl).assertExists()
    }
}