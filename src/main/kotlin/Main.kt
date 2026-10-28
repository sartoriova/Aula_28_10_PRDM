import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val coroutine = CoroutineScope(Dispatchers.Default).launch {
        println(getMessage())
    }
}

suspend fun getMessage(): String {
    delay(3000)
    return "Hello, world!"
}