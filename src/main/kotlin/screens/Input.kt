package screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Input(onBack: () -> Unit){


    var editedNumber: Int? by remember{ mutableStateOf(null) }
    var english by remember { mutableStateOf("") }
    var polish by remember { mutableStateOf("") }
    val translations = remember { ReadFromFile() }


    DefaultScreenLayout(onBack = onBack){
        Column {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                TextField(
                    value = english,
                    onValueChange = { english = it },
                    label = { Text("Po angielsku") },
                    maxLines = 1,
                    modifier = Modifier.width(400.dp).height(80.dp).padding(5.dp)
                )

                TextField(
                    value = polish,
                    onValueChange = {polish = it},
                    label = { Text("Po polsku") },
                    maxLines = 1,
                    modifier = Modifier.width(400.dp).height(80.dp).padding(5.dp)
                )

                OutlinedButton(
                    modifier = Modifier.width(150.dp).height(80.dp).padding(5.dp),
                    onClick = {

                        val gluedText = "$english - $polish\n"
                        if(editedNumber == null){
                            WriteToFile(gluedText)
                            translations.add(gluedText)
                            english = ""
                            polish = ""
                        } else {
                            translations[editedNumber!!] = gluedText
                            EditLineInFile(editedNumber!!, "$english - $polish")
                            editedNumber = null
                            english = ""
                            polish = ""
                        }

                    }


                ){
                    Text(if(editedNumber == null) "Dodaj" else "Zaktualizuj")
                }
            }

            Spacer(modifier = Modifier.height(100.dp))

            LazyColumn {
                items(translations.size){

                    Row(modifier = Modifier.fillMaxWidth().height(70.dp), horizontalArrangement = Arrangement.Center) {

                        Text(translations[it], style = MaterialTheme.typography.h5, textAlign = TextAlign.Start, modifier = Modifier.width(700.dp))
                        IconButton(onClick = {
                            translations.removeAt(it)
                            DeleteFromFile(it)

                        }){
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                        }

                        IconButton(onClick = {
                            val tableSplit: List<String> = translations[it].split(" - ")
                            english = tableSplit[0]
                            polish = tableSplit[1]
                            editedNumber = it
                        }){
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                        }

                    }
                }
            }
        }

    }
}