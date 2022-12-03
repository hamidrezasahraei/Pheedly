package sahraei.hamidreza.pheedly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import sahraei.hamidreza.pheedly.common.compose.AddFeedDialog
import sahraei.hamidreza.pheedly.feature.feeddetails.ui.FeedDetailsScreen
import sahraei.hamidreza.pheedly.feature.feedlist.ui.FeedListScreen
import sahraei.hamidreza.pheedly.feature.webview.ui.WebviewScreen
import sahraei.hamidreza.pheedly.navigation.Actions
import sahraei.hamidreza.pheedly.navigation.Screen
import sahraei.hamidreza.pheedly.navigation.Screen.Webview.WebviewArgs.Link
import sahraei.hamidreza.pheedly.ui.theme.PheedlyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PheedlyApp()
        }
    }
}

@Composable
fun PheedlyApp() {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }
    PheedlyTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.FeedList.route
            ) {
                composable(Screen.FeedList.route) {
                    FeedListScreen(
                        onFeedClicked = actions.openFeedDetail
                    )
                }
                composable(Screen.FeedDetail.route) {
                    FeedDetailsScreen(
                        onFeedArticleClicked = actions.openWebview
                    )
                }
                composable(Screen.Webview.route) {
                    val link = it.arguments?.getString(Link)
                    link?.let {
                        WebviewScreen(link = link)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PheedlyTheme {
        FeedListScreen(
            onFeedClicked = { println("On Feed $it Clicked") }
        )
    }
}