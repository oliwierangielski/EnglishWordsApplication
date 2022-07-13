package theme



import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.sp


private val Poppins = FontFamily(
    Font(
        resource = "font/Poppins-Regular.ttf",
        weight = FontWeight.W400,
        style = FontStyle.Normal
    ),
    Font(
        resource = "font/Poppins-Medium.ttf",
        weight = FontWeight.W500,
        style = FontStyle.Normal
    ),
    Font(
        resource = "font/Poppins-SemiBold.ttf",
        weight = FontWeight.W600,
        style = FontStyle.Normal
    ),
    Font(
        resource = "font/Poppins-Light.ttf",
        weight = FontWeight.W300,
        style = FontStyle.Normal
    )
)

val EnglishWordsApplicationTypography = Typography(
    h4 = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.W500,
        fontSize = 25.sp
    ),
    h5 = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp
    ),
    h6 = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.W500,
        fontSize = 15.sp
    ),
)