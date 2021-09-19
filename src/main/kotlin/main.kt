
fun main(args: Array<String>) {
    val databases: MutableMap <String, Database>
    while (true) {
        val input = readLine() ?: break
        val command = input.trim().split("[ ]+".toRegex())
        println(command)
        when (command[0]) {
            "get" -> getByKey(command.drop(1))
            "Get" -> getByValue(command.drop(1))
            "add" -> addByKey(command.drop(1), false)
            "Add" -> addByKey(command.drop(1), true)
            "delete" -> deleteByKey(command.drop(1))
            "Delete" -> deleteByValue(command.drop(1))
            "merge" -> mergeTwoDatabases(command.drop(1), false)
            "Merge" -> mergeTwoDatabases(command.drop(1), true)
            "open" -> {
                TODO()
            }
            "close" -> {
                TODO()
            }
            "Close" -> { // close without saving
                TODO()
            }
            "exit" -> break
            else -> {
                println("incorrect input")
            }
        }
    }
}

data class Database(val filepath: String, val data: MutableMap <String, String>)

fun getByKey(command: List<String>) { TODO() }
fun getByValue(command: List<String>) { TODO() }
fun addByKey(command: List<String>, overwrite: Boolean) { TODO() }
fun deleteByKey(command: List<String>) { TODO() }
fun deleteByValue(command: List<String>) { TODO() }
fun mergeTwoDatabases(command: List<String>, overwrite: Boolean) { TODO() }
fun saveDatabaseToFile(command: List<String>) { TODO() }
