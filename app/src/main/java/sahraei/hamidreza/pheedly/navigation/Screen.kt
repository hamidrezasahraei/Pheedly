package sahraei.hamidreza.pheedly.navigation

import androidx.navigation.NavHostController
import sahraei.hamidreza.pheedly.navigation.Screen.FeedDetail.FeedDetailArgs.FeedLink

sealed class Screen(val route: String) {
    object FeedList : Screen("FeedList")

    object FeedDetail : Screen("FeedDetail/{$FeedLink}") {
        object FeedDetailArgs {
            const val FeedLink = "FeedLink"
        }
        fun createRoute(feedLink: String) = "FeedDetail/$feedLink"
    }
}

class Actions(navHostController: NavHostController) {
    val openFeedDetail: (String) -> Unit = { feedLink ->
        navHostController.navigate(Screen.FeedDetail.createRoute(feedLink))
    }

    val navigateHome: () -> Unit = {
        navHostController.popBackStack(
            route = Screen.FeedList.route,
            inclusive = false
        )
    }

    val navigateBack: () -> Unit = {
        navHostController.navigateUp()
    }
}