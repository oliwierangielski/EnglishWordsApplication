package screens

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

fun EditLineInFile(editLine: Int, gluedText: String){

    val file = CreateAndReturnFile()

    val lines = file.readLines().toMutableList()
    lines[editLine] = gluedText

    val string = lines.joinToString("\n")
    file.writeText(string)
}

fun DeleteFromFile(deleteLine: Int, isLastElement: Boolean){
    val file = CreateAndReturnFile()

    val lines = file.readLines().toMutableList()



    lines.removeAt(deleteLine)

    if(!isLastElement){
        lines[lines.lastIndex] = lines.last() + "\n"
    }
    val string = lines.joinToString("\n")
    file.writeText(string)

}

fun WriteToFile(gluedText: String){
    val file = CreateAndReturnFile()
    file.appendText(gluedText)


    println(Paths.get("").toAbsolutePath().toString())
}

fun ReadFromFile(): SnapshotStateList<String> {

    val file = CreateAndReturnFile()
    return file.readLines().toMutableStateList()
}

fun CreateAndReturnFile(): File {
    val path: Path = Paths.get(System.getProperty("user.home") + "/Documents/EnglishWordApplication")
    val pathToFile: Path = Paths.get(System.getProperty("user.home") + "/Documents/EnglishWordApplication/words.txt")
    if (Files.notExists(path)) {
        File(path.toString()).mkdir()
    }
    if (Files.notExists(pathToFile)) {
        File(pathToFile.toString()).createNewFile()
    }
    return File(pathToFile.toString())
}