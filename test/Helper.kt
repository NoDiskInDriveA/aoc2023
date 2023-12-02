import java.io.FileNotFoundException

class Helper {
    companion object {
        fun getInput(str: String): List<String> {
            return this::class.java
                .getResource(str)
                ?.readText()
                ?.trim()
                ?.lines()
                ?: throw FileNotFoundException(str)
        }
    }
}
