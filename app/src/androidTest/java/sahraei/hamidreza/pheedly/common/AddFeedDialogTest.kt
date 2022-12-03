package sahraei.hamidreza.pheedly.common

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.StateRestorationTester
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test
import sahraei.hamidreza.pheedly.common.compose.AddFeedDialog
import sahraei.hamidreza.pheedly.common.compose.AddFeedTextFieldTag

class AddFeedDialogTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun onRecreation_dialogStateIsRestored() = runComposeUiTest{
        val feedUrl = "https://www.varzesh3.com/rss/all"
        val restorationTester = StateRestorationTester(this)
        restorationTester.setContent {
            AddFeedDialog(onConfirmClicked = { "Add Clicked" })
        }
        onNodeWithTag(AddFeedTextFieldTag).assertIsDisplayed()
        onNodeWithTag(AddFeedTextFieldTag).performTextInput(feedUrl)
        waitUntil {
            onAllNodesWithText(feedUrl).fetchSemanticsNodes().size == 1
        }
        restorationTester.emulateSaveAndRestore()
        onNodeWithTag(AddFeedTextFieldTag).assert(hasText(feedUrl))
    }
}