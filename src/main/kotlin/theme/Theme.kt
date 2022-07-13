package theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColors = lightColors(
    primary = Green700,
    primaryVariant = Green900,
    onPrimary = Color.White,
    secondary = Green700,
    secondaryVariant = Green900,
    onSecondary = Color.White,
    error = Color.Red,
    surface = White200
)

private val DarkColors = darkColors(
    primary = Green300,
    primaryVariant = Green700,
    onPrimary = Color.Black,
    secondary = Green300,
    onSecondary = Color.Black,
    error = Color.Red,
    onBackground = Color.White,
    surface = Black800
)


@Composable
fun EnglishWordsApplicationTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
){
    MaterialTheme(
        colors = if (darkTheme) DarkColors else LightColors,
        typography = EnglishWordsApplicationTypography,
        shapes = EnglishWordsApplicationShapes,
        content = content
    )
}