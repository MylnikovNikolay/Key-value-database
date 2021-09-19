import java.io.File

data class Databases(val databases: MutableMap<String, Database>) {

    fun runCommand(args: List<String>) {
        val command = args[0]
        val arguments = args.drop(1)

        when (command) {
            "exist" -> {
                println(
                    if (exist(arguments))
                        "Yes"
                    else
                        "No"
                )
            }

            "create" -> createNew(arguments)

            "delete" -> delete(arguments)

            "open" -> openFromFile(arguments)

            "close" -> {
                saveToFile(arguments)
                close(arguments)
            }

            "Close" -> close(arguments)

            "merge" -> mergeTwoDatabases(arguments)

            else -> {
                if (arguments.isEmpty() || command !in listOfCommands) {
                    incorrectInputErrorMessage()
                    return
                }
                val database = arguments.first()
                if (exist(database)) {
                    databases[database]?.runCommand(arguments.drop(1))
                } else {
                    notExistErrorMessage(database)
                }
            }
        }
    }

    private fun exist(args: List<String>): Boolean {
        if (args.size != 1) {
            incorrectInputErrorMessage()
            return false
        }
        return databases.containsKey(args[0])
    }

    private fun exist(databaseName: String): Boolean = exist(listOf(databaseName))

    private fun createNew(args: List<String>) {
        if (args.size != 2) {
            incorrectInputErrorMessage()
            return
        }
        val databaseName = args[0]
        val filepath = args[1]
        val file = File(filepath)
        if (!file.createNewFile()) {
            println("$filepath already exist")
            return
        }
        databases[databaseName] = Database(filepath, mutableMapOf())
    }

    private fun delete(args: List<String>) {
        if (args.size != 1) {
            incorrectInputErrorMessage()
            return
        }
        if (!exist(args)) {
            notExistErrorMessage(args)
            return
        }
        val file = File(databases[args[0]]!!.filepath)
        file.delete()
        databases.remove(args[0])
    }

    private fun openFromFile(args: List<String>) {
        if (args.size != 2) {
            incorrectInputErrorMessage()
            return
        }
        val databaseName = args[0]
        if (exist(databaseName)) {
            println("database with '$databaseName' already exist")
            return
        }
        val filepath = args[1]
        val file = File(filepath)
        if (!file.exists()) {
            println("file doesn't exist")
            return
        }

        databases[databaseName] = Database(filepath, mutableMapOf())
        val input = file.readLines()
        for (line in input) {
            val values = line.split(' ')
            databases[databaseName]!!.data[values[0]] = values[1]
        }
    }

    private fun saveToFile(args: List<String>) {
        if (args.size != 2) {
            incorrectInputErrorMessage()
            return
        }
        val databaseName = args[0]
        if (!exist(databaseName)) {
            notExistErrorMessage(databaseName)
            return
        }
        val file = File(args[1])
        if (!file.exists()) {
            println("file doesn't exist")
            return
        }
        val output = StringBuilder()
        for (entry in databases[databaseName]!!.data) {
            output.append(entry.key)
                  .append(' ')
                  .append(entry.value)
                  .append('\n')
        }
        file.writeText(output.toString())
    }

    private fun close(args: List<String>) {
        if (args.size != 1) {
            incorrectInputErrorMessage()
            return
        }
        if (!exist(args)) {
            notExistErrorMessage(args)
            return
        }
        databases.remove(args[0])
    }

    private fun mergeTwoDatabases(args: List<String>) {
        if (args.size != 2) {
            incorrectInputErrorMessage()
            return
        }
        if (!exist(args[0])) {
            notExistErrorMessage(args[0])
        }
        if (!exist(args[1])) {
            notExistErrorMessage(args[1])
        }
        databases[args[0]]!!.data += databases[args[1]]!!.data
    }
}
