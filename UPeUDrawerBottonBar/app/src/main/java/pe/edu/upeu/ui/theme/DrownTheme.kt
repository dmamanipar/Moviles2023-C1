package pe.edu.upeu.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkDrownColorPalette = darkColors(
    primary = Drown200,
    primaryVariant = Drown700,
    secondary = DrownRed200,
    onPrimary = Color.White,
    onSecondary = Color.Black
)
private val LightDrownColorPalette = lightColors(
    primary = Drown500,
    primaryVariant = Drown700,
    secondary = DrownRed200,
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
fun DrownTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) DarkDrownColorPalette else LightDrownColorPalette
    UPeUDrawerBottonBarTheme(colors, content)
}