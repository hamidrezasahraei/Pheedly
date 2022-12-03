package sahraei.hamidreza.pheedly.common.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

const val ProgressBarTag = "Progress"

@Composable
fun PheedlyProgressItem() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.Center)
                .testTag(ProgressBarTag)
        )
    }
}