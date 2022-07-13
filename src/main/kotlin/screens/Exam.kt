package screens


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Exam(onBack: () -> Unit) {


    var translations = remember { ReadFromFile() }
    val fullTranslations = remember { translations.toList() }
    var points: Int by remember { mutableStateOf(0) }
    val maxPoints: Int = remember { translations.size }
    var isGameFinished: Boolean by remember { mutableStateOf(false) }
    var goodAnswerPosition: Int = remember { (0..3).random() }
    var goodAnswerPosInList: Int = remember { (0 until translations.size).random() }
    var possibleAnswers: Array<String?> = remember { PrepareAnswers(translations, fullTranslations, goodAnswerPosInList, goodAnswerPosInList) }





    if (maxPoints < 4) {
        Text("Brak Słów. Dodaj je")
    } else {



        fun setupAfterAnswer(answer: Int){

            if (translations.size == 1){
                isGameFinished = true
            } else {
                translations.removeAt(goodAnswerPosInList)
                if(answer == goodAnswerPosition) points++
            }
            //reset after every question
            goodAnswerPosition = (0..3).random()
            goodAnswerPosInList = (0 until translations.size).random()
            possibleAnswers = PrepareAnswers(translations, fullTranslations, goodAnswerPosInList, goodAnswerPosInList)


        }


    DefaultScreenLayout(onBack = onBack, contentAlignment = Alignment.Center){
            Surface(modifier = Modifier.size(800.dp, 700.dp)) {
               if(!isGameFinished) {
                   Row(
                       modifier = Modifier.fillMaxWidth().padding(10.dp),
                       horizontalArrangement = Arrangement.SpaceBetween
                   ) {
                       Text("Runda: ${maxPoints - translations.size + 1}")
                       Text("$points/$maxPoints")
                   }
               }

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        if(!isGameFinished){
                            Text(translations[goodAnswerPosInList].split(" - ")[0], style = MaterialTheme.typography.h2, textAlign = TextAlign.Center)

                            Row {
                                Column {
                                    Button(
                                        onClick = {
                                            setupAfterAnswer(0)
                                        },
                                        modifier = Modifier.width(200.dp).height(70.dp).padding(5.dp)
                                    ){
                                        Text(possibleAnswers[0]!!, textAlign = TextAlign.Center)
                                    }
                                    Button(
                                        onClick = {
                                            setupAfterAnswer(1)
                                        },
                                        modifier = Modifier.width(200.dp).height(70.dp).padding(5.dp)
                                    ){
                                        Text(possibleAnswers[1]!!, textAlign = TextAlign.Center)
                                    }
                                }
                                Column {
                                    Button(
                                        onClick = {
                                            setupAfterAnswer(2)
                                        },
                                        modifier = Modifier.width(200.dp).height(70.dp).padding(5.dp)
                                    ){
                                        Text(possibleAnswers[2]!!, textAlign = TextAlign.Center)
                                    }
                                    Button(
                                        onClick = {
                                            setupAfterAnswer(3)
                                        },
                                        modifier = Modifier.width(200.dp).height(70.dp).padding(5.dp)
                                    ){
                                        Text(possibleAnswers[3]!!, textAlign = TextAlign.Center)
                                    }
                                }
                            }
                        } else {
                            Text("Koniec Gry. Wynik to $points/$maxPoints", style = MaterialTheme.typography.h2)
                            OutlinedButton(onClick = {
                                isGameFinished = false
                                translations = ReadFromFile()
                                points = 0
                            }){
                                Text("Zagraj jeszcze raz")
                            }
                        }



                    }

                }


            }
        }



    }
}

fun PrepareAnswers(translations: List<String?>, fullTranslations: List<String>, randomWorld: Int, goodPosition: Int): Array<String?>{




    val goodAnswersTable = translations[randomWorld]!!.split(" - ")


    val translationsCopy = fullTranslations.toMutableList()

    translationsCopy.remove(translations[randomWorld])

    val answers: Array<String?> = arrayOfNulls(4)


    for(i in 0..3){
        if (i == goodPosition){
            answers[i] = goodAnswersTable[1]
        } else {
            val newRandom = (0 until translationsCopy.size).random()
            answers[i] = translationsCopy[newRandom].split(" - ")[1]
            translationsCopy.removeAt(newRandom)
        }
    }


    return  answers

}