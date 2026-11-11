import kotlinx.coroutines.*

var start = 0L

fun log(msg: String) = println("[${System.currentTimeMillis() - start} ms] $msg")

suspend fun task(name: String, ms: Long) {
    try {
        delay(ms)
        log("$name completed")
    } catch (e: CancellationException) {
        log("$name canceled")
        throw e
    }
}

suspend fun cancelOneChildCoroutine() = coroutineScope {
    launch {task("A", 1000)}
    val b = launch {task("B", 1000)}
    launch {task("C", 1000)}

    delay(300)
    b.cancel()

    log("B.cancel() executed")
}

suspend fun childFailedWithCoroutineScope() {
    try {
        coroutineScope {
            launch{task("A", 1000)}
            launch {
                delay(300)
                throw IllegalStateException("B failed")
            }
            launch {task("C", 1000)}
        }
        log("Upper completed")
    } catch (e: IllegalStateException) {
        log("Upper received the error: ${e.message}")
    }
}

suspend fun childFailedWithSupervisorScope() {
    val handler = CoroutineExceptionHandler{_, e -> log("handler got: ${e.message}")}

    supervisorScope {
        launch{task("A", 1000)}
        launch(handler) {
            delay(300)
            throw IllegalStateException("B failed")
        }
        launch{task("C", 1000)}
    }
    log("Upper completed")
}

suspend fun main() {
    println("1 - Cancel a child")
    start = System.currentTimeMillis()
    cancelOneChildCoroutine()

    println("2 - coroutineScope")
    start = System.currentTimeMillis()
    childFailedWithCoroutineScope()

    println("3 - supervisorScope")
    start = System.currentTimeMillis()
    childFailedWithSupervisorScope()
}

//dessa forma não precisa do suspend
//fun main() = runBlocking {
//    println("1 - Cancel a child")
//    start = System.currentTimeMillis()
//    cancelOneChildCoroutine()
//
//    println("2 - coroutineScope")
//    start = System.currentTimeMillis()
//    childFailedWithCoroutineScope()
//}