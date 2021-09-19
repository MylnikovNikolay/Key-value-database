val listOfCommands = listOf("contains", "get", "Get", "add", "Add", "delete", "Delete")

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
            else -> incorrectInputErrorMessage()
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
