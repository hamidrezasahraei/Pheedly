package sahraei.hamidreza.pheedly.common.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import sahraei.hamidreza.pheedly.ui.theme.LightGreys90
import sahraei.hamidreza.pheedly.ui.theme.LightPheedly

@Composable
fun ScaffoldWithTopBar(
    title: String,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = LightPheedly,
                title = {
                    Text(
                        text = title,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = LightGreys90
                    )
                })
        },
        backgroundColor = MaterialTheme.colors.primary
    ) {
        content.invoke()
    }
}