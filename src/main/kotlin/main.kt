
fun main() {
    val mapOfDatabases = MapOfDatabases(mutableMapOf())
    while (true) {
        val input = readLine() ?: break
        if (runCommand(input, mapOfDatabases)) {
            break
        }
    }
}

fun runCommand(input: String, mapOfDatabases: MapOfDatabases): Boolean {
    val command = splitInput(input)

    if (command[0] == "exit") {
        if (mapOfDatabases.databases.isNotEmpty()) {
            println("Some databases are still open")
            return false
        }
        return true
    } else {
        mapOfDatabases.runCommand(command)
    }
    return false
}

fun splitInput(input: String): List<String> {
    return input.trim().split("[ ]+".toRegex())
}

fun incorrectInputErrorMessage() {
    println("incorrect input")
}

fun notExistErrorMessage(name: String) {
    println("database '${name}' doesn't exist")
}

fun notExistErrorMessage(name: List<String>) {
    notExistErrorMessage(name[0])
}
