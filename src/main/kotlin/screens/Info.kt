package screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Info(onBack: () -> Unit){
    DefaultScreenLayout(onBack = onBack, contentAlignment = Alignment.Center){
        Surface(modifier = Modifier.size(800.dp, 700.dp)) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Column {
                    Text("English Words Application", modifier = Modifier.padding(10.dp))
                    Text("© Wszystkie prawa Zastrzeżone", modifier = Modifier.padding(10.dp))
                    Text("2022", modifier = Modifier.padding(10.dp))

                }

            }


        }
    }

}