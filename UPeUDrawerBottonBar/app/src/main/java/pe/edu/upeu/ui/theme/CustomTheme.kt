package pe.edu.upeu.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkGreenColorPalette = darkColors(
    primary = Green200,
    primaryVariant = Green700,
    secondary = OranRed200,
    onPrimary = Color.White,
    onSecondary = Color.Black
)
private val LightGreenColorPalette = lightColors(
    primary = Green500,
    primaryVariant = Green700,
    secondary = OranRed200,
    onPrimary = Color.White,
    onSecondary = Color.Black,
/*background = Color.White,
surface = Color.White,
onPrimary = Color.White,
onSecondary = Color.Black,
onBackground = Color.Black,
onSurface = Color.Black,*/
)

@Composable
fun GreenTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) DarkGreenColorPalette else LightGreenColorPalette
    UPeUDrawerBottonBarTheme(colors, content)
}