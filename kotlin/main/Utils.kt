import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("kotlin/resources/$name.txt").readText().lines().dropLast(1)

data class Vec(val y: Int, val x: Int)

fun <T> MutableList<T>.swap(a: Int, b: Int) {
    this[a] = this[b].also { this[b] = this[a] }
}
