package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import com.arkivanov.decompose.router.pop
import com.arkivanov.decompose.router.push
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.github.tkuenneth.nativeparameterstoreaccess.Dconf
import com.github.tkuenneth.nativeparameterstoreaccess.MacOSDefaults
import com.github.tkuenneth.nativeparameterstoreaccess.NativeParameterStoreAccess
import com.github.tkuenneth.nativeparameterstoreaccess.WindowsRegistry
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import navigator.rememberRouter
import theme.EnglishWordsApplicationTheme

fun isSystemInDarkTheme(): Boolean = when {
    NativeParameterStoreAccess.IS_WINDOWS -> {
        val result = WindowsRegistry.getWindowsRegistryEntry(
            "HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize",
            "AppsUseLightTheme")
        result == 0x0
    }
    NativeParameterStoreAccess.IS_MACOS -> {
        val result = MacOSDefaults.getDefaultsEntry("AppleInterfaceStyle")
        result == "Dark"
    }
    Dconf.HAS_DCONF -> {
        val result = Dconf.getDconfEntry("/org/gnome/desktop/interface/gtk-theme")
        result.toLowerCase().contains("dark")
    }
    else -> false
}



@Composable
fun DefaultScreenLayout(contentAlignment: Alignment = Alignment.TopStart, onBack: () -> Unit,  content: @Composable () -> Unit){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { BackButton(onBack) }
    ){
        Box(contentAlignment = contentAlignment, modifier = Modifier.fillMaxSize()){
            content()
        }

    }
}

@Composable
fun BackButton(onClick: () -> Unit){
    IconButton(onClick = onClick){
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colors.onBackground)
    }
}

@Composable
fun CustomButton(text: String, onClick: () -> Unit){
    Button(onClick = onClick, modifier = Modifier.width(300.dp).height(80.dp).padding(10.dp)){
        Text(text = text, style = MaterialTheme.typography.h5)
    }
}



@Composable
fun Main() {
    val router =
        rememberRouter<Screen>(
            initialConfiguration = { Screen.Home },
            handleBackButton = true
        )

    var isInDarkMode by remember { mutableStateOf(isSystemInDarkTheme()) }



    EnglishWordsApplicationTheme(darkTheme = isInDarkMode) {
        Box(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)
        ) {

                Children(
                    routerState = router.state,
                    animation = crossfadeScale()
                ) { screen ->
                    when (val configuration = screen.configuration) {
                        is Screen.Home -> Home(onItemClick = { router.push(it) })
                        is Screen.Exam -> Exam(onBack = router::pop)
                        is Screen.Input -> Input(onBack = router::pop)
                        is Screen.Info -> Info(onBack = router::pop)
                    }
                }
        }
    }

    LaunchedEffect(isSystemInDarkTheme()) {
        while (isActive) {
            val newMode = isSystemInDarkTheme()
            if (isInDarkMode != newMode) {
                isInDarkMode = newMode
            }
            delay(1000)
        }
    }
}



sealed class Screen : Parcelable {

    @Parcelize
    object Home : Screen()

    @Parcelize
    object Exam : Screen()

    @Parcelize
    object Input: Screen()

    @Parcelize
    object Info: Screen()

}