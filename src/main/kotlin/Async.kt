import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

suspend fun main() {
    val coroutineScope = CoroutineScope(Dispatchers.Default)

    val start = System.currentTimeMillis()
    val helloJob = coroutineScope.async {
        getHello()
    }

    val worldJob = coroutineScope.async {
        getWorld()
    }

    println("${helloJob.await()} ${worldJob.await()}")
    println(System.currentTimeMillis() - start)
}

suspend fun getHello(): String {
    delay(5000)
    return "Hello"
}

suspend fun getWorld(): String {
    delay(500)
    return "World"
}