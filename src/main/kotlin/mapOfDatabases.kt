import java.io.File
import java.lang.Integer.max

data class MapOfDatabases(val databases: MutableMap<String, Database>) {

    fun runCommand(args: List<String>) {
        val command = args[0]
        val arguments = args.drop(1)

        when (command) {
            "exist" -> printExist(arguments)

            "create" -> createNew(arguments)

            "delete" -> delete(arguments)

            "deleteAll" -> deleteAll()

            "open" -> openFromFile(arguments)

            "close" -> {
                saveToFile(arguments)
                close(arguments)
            }

            "Close" -> close(arguments)

            "closeAll" -> {
                saveAll()
                closeAll()
            }

            "CloseAll" -> closeAll()

            "merge" -> mergeTwoDatabases(arguments)

            "list" -> printAllOpenDB()

            else -> {
                if (arguments.isEmpty()) {
                    incorrectInputErrorMessage()
                    return
                }
                val database = arguments.first()
                databases[database]?.runCommand(listOf(command) + arguments.drop(1))
                    ?: notExistErrorMessage(database)
            }
        }
    }

    private fun printExist(args: List<String>) {
        if (args.size != 1) {
            incorrectInputErrorMessage()
            return
        }
        println(
            if (exist(args[0]))
                "Yes"
            else
                "No"
        )
    }

    private fun exist(args: List<String>): Boolean {
        return databases.containsKey(args[0])
    }

    private fun exist(databaseName: String): Boolean = exist(listOf(databaseName))

    private fun createNew(args: List<String>) {
        if (args.size != 2) {
            incorrectInputErrorMessage()
            return
        }
        val databaseName = args[0]
        if (databases.containsKey(databaseName)) {
            println("database '$databaseName' already exist")
        }
        val filepath = args[1]
        val file = File(filepath)
        if (!file.createNewFile()) {
            println("file '$filepath' already exist")
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

    private fun deleteAll() {
        val keys = databases.keys.toList()
        for (database in keys) {
            delete(listOf(database))
        }
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
        file.forEachLine {
            val values = it.split(' ')
            databases[databaseName]!!.data[values[0]] = values[1]
        }
    }

    private fun saveToFile(args: List<String>) {
        if (args.size != 1) {
            incorrectInputErrorMessage()
            return
        }
        val databaseName = args[0]
        if (!exist(databaseName)) {
            return
        }
        val file = File(databases[args[0]]!!.filepath)
        if (!file.exists()) {
            file.createNewFile()
        }

        file.writeText("")
        databases[databaseName]!!.data.forEach {
            file.appendText("${it.key} ${it.value}\n")
        }
    }

    private fun saveAll() {
        databases.forEach {
            saveToFile(listOf(it.key))
        }
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

    private fun closeAll() {
        val keys = databases.keys.toList()
        for (database in keys) {
            close(listOf(database))
        }
    }

    private fun mergeTwoDatabases(args: List<String>) {
        if (args.size != 2) {
            incorrectInputErrorMessage()
            return
        }
        if (!exist(args[0])) {
            notExistErrorMessage(args[0])
            return
        }
        if (!exist(args[1])) {
            notExistErrorMessage(args[1])
            return
        }
        databases[args[0]]!!.data += databases[args[1]]!!.data
    }

    private fun printAllOpenDB() {
        if (databases.isEmpty()) {
            println("no open databases")
            return
        }
        var maxLen = 6
        databases.forEach {
            maxLen = max(maxLen, it.key.length)
        }
        val sb = StringBuilder()
        sb.append("name:".padEnd(maxLen + 1))
        sb.append("filepath:\n")
        databases.forEach {
            sb.append(it.key.padEnd(maxLen + 1))
            sb.append(it.value.filepath)
            sb.append("\n")
        }
        print(sb.toString())
    }
}
