package screens

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import java.io.File
import java.nio.file.Paths

fun EditLineInFile(editLine: Int, gluedText: String){
    val fileName = "words.txt"
    val file = File(fileName)

    val lines = file.readLines().toMutableList()
    lines[editLine] = gluedText

    val string = lines.joinToString("\n")
    file.writeText(string)
}

fun DeleteFromFile(deleteLine: Int){
    val fileName = "words.txt"
    val file = File(fileName)

    val lines = file.readLines().toMutableList()



    lines.removeAt(deleteLine)

    val getLastIndex = lines.last()
    lines[lines.lastIndex] = lines.last() + "\n"

    val string = lines.joinToString("\n")
    file.writeText(string)
}

fun WriteToFile(gluedText: String){
    val fileName = "words.txt"
    val file = File(fileName)

    file.createNewFile()
    file.appendText(gluedText)


    println(Paths.get("").toAbsolutePath().toString())
}

fun ReadFromFile(): SnapshotStateList<String> {

    val file = File("words.txt")

    file.createNewFile()

    return file.readLines().toMutableStateList()
}