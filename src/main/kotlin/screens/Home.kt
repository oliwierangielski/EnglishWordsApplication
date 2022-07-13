package screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Home(onItemClick: (Screen) -> Unit){

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        CustomButton("Dodawanie słówek"){
            onItemClick(Screen.Input)
        }
        CustomButton("Quiz słówek"){
            onItemClick(Screen.Exam)
        }

        CustomButton("Informacje o programie"){
            onItemClick(Screen.Info)
        }
    }
}