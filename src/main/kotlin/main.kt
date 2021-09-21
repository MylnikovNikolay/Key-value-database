
fun main() {
    val databases = Databases(mutableMapOf())
    while (true) {
        val input = readLine() ?: break
        val command = input.trim().split("[ ]+".toRegex())

        if (command.isEmpty()) {
            incorrectInputErrorMessage()
            continue
        }
        if (command[0] == "exit") {
            break
        } else {
            databases.runCommand(command)
        }
    }
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
