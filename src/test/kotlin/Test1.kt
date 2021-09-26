import java.io.ByteArrayOutputStream
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
    fun testMain1() {
        val testDBs = MapOfDatabases(mutableMapOf())
        runCommand("", testDBs)
        assertEquals(incorrectInput, stream.toString().trim())

        assertEquals(true, runCommand("exit", MapOfDatabases(mutableMapOf())))

        testDBs.runCommand(splitInput("deleteAll"))
    }

    @Test
    fun testMain2() {
        val testDBs = MapOfDatabases(mutableMapOf())
        testDBs.runCommand(splitInput("create a test"))
        runCommand("exit", testDBs)
        assertEquals("Some databases are still open", stream.toString().trim())

        testDBs.runCommand(splitInput("deleteAll"))
    }
}
