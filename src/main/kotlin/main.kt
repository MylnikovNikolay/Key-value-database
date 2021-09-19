
fun main(args: Array<String>) {
    val databases = Databases(mutableMapOf())
    while (true) {
        val input = readLine() ?: break
        val command = input.trim().split("[ ]+".toRegex())

        if (command.isEmpty()) {
            incorrectInput()
            continue
        }
        if (command[0] == "exit") {
            break
        } else {
            databases.runCommand(command)
        }
    }
}

fun incorrectInput() {
    println("incorrect input")
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

            "merge" -> mergeTwoDatabases(arguments, false)

            "Merge" -> mergeTwoDatabases(arguments, true)

            else -> {
                if (arguments.isEmpty()) {
                    incorrectInput()
                    return
                }
                val database = arguments.first()
                if (exist(listOf(database))) {
                    databases[database]?.runCommand(arguments.drop(1))
                } else {
                    println("database doesn't exist")
                }
            }
        }
    }

    private fun exist(args: List<String>): Boolean {
        TODO()
    }

    private fun createNew(args: List<String>) {
        TODO()
    }

    private fun delete(args: List<String>) {
        TODO()
    }

    private fun openFromFile(args: List<String>) {
        TODO()
    }

    private fun saveToFile(args: List<String>) {
        TODO()
    }

    private fun close(args: List<String>) {
        TODO()
    }

    private fun mergeTwoDatabases(args: List<String>, overwrite: Boolean) {
        TODO()
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
