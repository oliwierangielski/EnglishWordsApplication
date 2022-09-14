// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import screens.Main


const val MIN_WIDTH=1000
const val MIN_HEIGHT = 1000

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState( width = MIN_WIDTH.dp, height = MIN_HEIGHT.dp),
        title = "English Words Application",
        icon = painterResource("images/icon.png")
    ) {
        Main()
    }
}






