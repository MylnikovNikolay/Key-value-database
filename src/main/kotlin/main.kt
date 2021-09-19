import java.io.File

fun main(args: Array<String>) {
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
    println("database '${name[0]}' doesn't exist")
}

data class Databases(val databases: MutableMap<String, Database>) {

    fun runCommand(args: List<String>) {
        val command = args[0]
        val arguments = args.drop(1)

        when (command) {
            "exist" -> {
                println(
                    if (exist(args))
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
                if (arguments.isEmpty()) {
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
    }

    private fun openFromFile(args: List<String>) {
        TODO()
    }

    private fun saveToFile(args: List<String>) {
        TODO()
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

data class Database(val filepath: String, val data: MutableMap <String, String>) {

    fun runCommand(args: List<String>) {
        val command = args[0]
        val arguments = args.drop(1)
        when (command) {
            "contains" -> {
                containsKey(arguments)
            }
            "get" -> {
                getByKey(arguments)
            }
            "Get" -> {
                getKeysByValue(arguments)
            }
            "add" -> {
                addByKey(arguments, false)
            }
            "Add" -> {
                addByKey(arguments, true)
            }
            "delete" -> {
                deleteByKey(arguments)
            }
            "Delete" -> {
                deleteByValue(arguments)
            }
            else -> println("incorrect input")
        }
    }

    private fun containsKey(args: List<String>) {
        TODO()
    }

    private fun getByKey(args: List<String>) {
        TODO()
    }

    private fun getKeysByValue(args: List<String>) {
        TODO()
    }

    private fun addByKey(args: List<String>, overwrite: Boolean) {
        TODO()
    }

    private fun deleteByKey(args: List<String>) {
        TODO()
    }

    private fun deleteByValue(args: List<String>) {
        TODO()
    }
}
