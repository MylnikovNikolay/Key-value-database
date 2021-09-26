import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.*

internal class Test1 {
    private val standardOut = System.out
    private val standardIn = System.`in`
    private val stream = ByteArrayOutputStream()
    private val incorrectInput = "incorrect input"

    @BeforeTest
    fun setUp() {
        System.setOut(PrintStream(stream))
    }

    @AfterTest
    fun tearDown() {
        System.setOut(standardOut)
        System.setIn(standardIn)
    }

    @Test
    fun test1() {
        val testDBs = MapOfDatabases(mutableMapOf())
        runCommand("", testDBs)
        assertEquals(incorrectInput, stream.toString().trim())

        assertEquals(true, runCommand("exit", MapOfDatabases(mutableMapOf())))

        testDBs.runCommand(splitInput("deleteAll"))
    }

    @Test
    fun test2() {
        val testDBs = MapOfDatabases(mutableMapOf())
        testDBs.runCommand(splitInput("create a test"))
        runCommand("exit", testDBs)
        assertEquals("Some databases are still open", stream.toString().trim())

        testDBs.runCommand(splitInput("deleteAll"))
    }

    @Test
    fun test3() {
        val testDBs = MapOfDatabases(mutableMapOf())
        testDBs.runCommand(splitInput("create a test"))

        val file = File("test")
        assertTrue(file.exists())

        testDBs.runCommand(splitInput("exist a"))
        testDBs.runCommand(splitInput("delete a"))
        testDBs.runCommand(splitInput("exist a"))

        assertEquals("""
            Yes
            No
        """.trimIndent(), stream.toString().trim())
    }

    @Test
    fun test4() {
        val testDBs = MapOfDatabases(mutableMapOf())
        testDBs.let {
            it.runCommand(splitInput("create a test1"))
            it.runCommand(splitInput("create b test2"))

            it.runCommand(splitInput("exist a"))
            it.runCommand(splitInput("exist b"))

            it.runCommand(splitInput("closeAll"))

            it.runCommand(splitInput("exist a"))
            it.runCommand(splitInput("exist b"))

            it.runCommand(splitInput("open a test1"))
            it.runCommand(splitInput("open b test2"))

            it.runCommand(splitInput("exist a"))
            it.runCommand(splitInput("exist b"))

            it.runCommand(splitInput("deleteAll"))

            it.runCommand(splitInput("exist a"))
            it.runCommand(splitInput("exist b"))
        }

        assertEquals("""
            Yes
            Yes
            No
            No
            Yes
            Yes
            No
            No
        """.trimIndent(), stream.toString().trim())
    }

    @Test
    fun test5() {
        val testDBs = MapOfDatabases(mutableMapOf())
        val check = StringBuilder()
        testDBs.let {
            it.runCommand(splitInput("create a test1"))
            it.runCommand(splitInput("create b test2"))

            it.runCommand(splitInput("add a 1 1"))
            it.runCommand(splitInput("add a 2 1"))
            it.runCommand(splitInput("add b 2 2"))
            it.runCommand(splitInput("add b 3 2"))

            it.runCommand(splitInput("contains a 1"))
            check.append("Yes\n")
            it.runCommand(splitInput("contains b 1"))
            check.append("No\n")
            it.runCommand(splitInput("contains b 2"))
            check.append("Yes\n")

            it.runCommand(splitInput("get a 1"))
            check.append("1\n")
            it.runCommand(splitInput("get a 2"))
            check.append("1\n")
            it.runCommand(splitInput("get a 3"))
            check.append("key doesn't exist\n")

            it.runCommand(splitInput("Get a 1"))
            check.append("1, 2\n")

            it.runCommand(splitInput("merge a b"))

            it.runCommand(splitInput("get a 1"))
            check.append("1\n")
            it.runCommand(splitInput("get a 2"))
            check.append("2\n")
            it.runCommand(splitInput("get a 3"))
            check.append("2\n")

            it.runCommand(splitInput("erase b 1"))
            it.runCommand(splitInput("erase b 2"))
            it.runCommand(splitInput("contains b 2"))
            check.append("No\n")

            it.runCommand(splitInput("Erase a 1"))
            it.runCommand(splitInput("contains a 1"))
            check.append("No\n")
            it.runCommand(splitInput("contains a 2"))
            check.append("Yes\n")
            it.runCommand(splitInput("contains a 3"))
            check.append("Yes\n")

            it.runCommand(splitInput("list"))
            check.append("""
                Name:  Filepath:
                a      test1
                b      test2
            """.trimIndent())

            it.runCommand(splitInput("deleteAll"))
        }

        assertEquals(check.toString().trim(), stream.toString().trim())
    }

    @Test
    fun testBig() {
        val entries = 1000000
        val testDBs = MapOfDatabases(mutableMapOf())
        testDBs.runCommand(splitInput("create a test1"))
        testDBs.runCommand(splitInput("create b test2"))
        repeat(entries){
            testDBs.runCommand(splitInput("add a $it $it"))
            testDBs.runCommand(splitInput("add b ${it+entries} ${it+entries}"))
        }

        testDBs.let {
            it.runCommand(splitInput("contains a 13021"))
            it.runCommand(splitInput("contains b ${21421 + entries}"))

            it.runCommand(splitInput("merge a b"))
            it.runCommand(splitInput("contains a 13021"))
            it.runCommand(splitInput("contains a ${21421 + entries}"))

            it.runCommand(splitInput("deleteAll"))
        }

        assertEquals("""
            Yes
            Yes
            Yes
            Yes
        """.trimIndent(), stream.toString().trim())
    }
}
