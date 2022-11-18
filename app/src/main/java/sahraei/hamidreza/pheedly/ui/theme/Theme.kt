package sahraei.hamidreza.pheedly.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = DarkGreys10,
    primaryVariant = DarkGreys40,
    secondary = LightPheedly,
    background = Color.Transparent,
    surface = DarkGreys20,
    onPrimary = DarkGreys90,
    onBackground = DarkGreys70,
    onSurface = DarkGreys90
)

private val LightColorPalette = lightColors(
    primary = LightGreys10,
    primaryVariant = LightGreys40,
    secondary = LightPheedly,
    background = Color.Transparent,
    surface = LightGreys20,
    onPrimary = LightGreys90,
    onBackground = LightGreys70,
    onSurface = LightGreys90
)

@Composable
fun PheedlyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}