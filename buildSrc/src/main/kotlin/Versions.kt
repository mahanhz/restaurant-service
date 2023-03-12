import java.io.File
import java.io.FileInputStream
import java.util.*

object Versions {
    var props = Properties()

    fun fromProperties(dir: File): Properties {
        if (props.isEmpty) {
            props = Properties().apply {
                val filePath = File(dir, "buildSrc/gradle.properties")
                load(FileInputStream(filePath))
            }
        }

        return props
    }
}
