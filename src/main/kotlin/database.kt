val listOfCommands = listOf("contains", "get", "Get", "add", "delete", "Delete")

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
                printKeysByValue(arguments)
            }
            "add" -> {
                addByKey(arguments)
            }
            "delete" -> {
                deleteByKey(arguments)
            }
            "Delete" -> {
                deleteByValue(arguments)
            }
            else -> incorrectInputErrorMessage()
        }
    }

    private fun containsKey(args: List<String>) {
        if (args.size != 1) {
            incorrectInputErrorMessage()
            return
        }
        println(
            if (data.containsKey(args[0]))
                "Yes"
            else
                "No"
        )
    }

    private fun getByKey(args: List<String>) {
        if (args.size != 1) {
            incorrectInputErrorMessage()
            return
        }
        if (!data.containsKey(args[0])) {
            println("key doesn't exist")
            return
        }
        println(data[args[0]])
    }

    private fun getKeysByValue(value: String): List<String> {
        val output = mutableListOf<String>()
        data.forEach {
            if (it.value == value) {
                output.add(it.key)
            }
        }
        return output
    }

    private fun printKeysByValue(args: List<String>) {
        if (args.size != 1) {
            incorrectInputErrorMessage()
            return
        }
        val output = getKeysByValue(args[0])
        println(output.joinToString(", "))
    }

    private fun addByKey(args: List<String>) {
        if (args.size != 2) {
            incorrectInputErrorMessage()
            return
        }
        data[args[0]] = args[1]
    }

    private fun deleteByKey(args: List<String>) {
        if (args.size != 1) {
            incorrectInputErrorMessage()
            return
        }
        data.remove(args[0])
    }

    private fun deleteByValue(args: List<String>) {
        if (args.size != 1) {
            incorrectInputErrorMessage()
            return
        }
        val keys = getKeysByValue(args[0])
        keys.forEach {
            data.remove(it)
        }
    }
}
