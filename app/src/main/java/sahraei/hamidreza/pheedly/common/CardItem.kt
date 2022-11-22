package sahraei.hamidreza.pheedly.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sahraei.hamidreza.pheedly.ui.theme.Typography

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    title: String,
    onCardClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onCardClick.invoke()
            },
        elevation = 8.dp
    ) {
        Text(
            text = title,
            style = Typography.body1,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}