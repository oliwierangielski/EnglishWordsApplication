package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@Composable
fun Home(onItemClick: (Screen) -> Unit){
    val translations = remember { ReadFromFile() }
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState, snackbarHost = { SnackbarHost(it){data -> Snackbar(backgroundColor = Color.Red, snackbarData = data)} }) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            CustomButton("Dodawanie słówek"){
                onItemClick(Screen.Input)
            }
            CustomButton("Quiz słówek"){
                if(translations.size >= 4){
                    onItemClick(Screen.Exam)
                } else {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Dodano zbyt mało słów aby przeprowadzić quiz")
                    }

                }

            }

            CustomButton("Informacje o programie"){
                onItemClick(Screen.Info)
            }

        }
    }

}