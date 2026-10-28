import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() {
    val coroutineJob = CoroutineScope(Dispatchers.Default).launch {
        println(getMessage())
    }
    //coroutine.join()

    println("ANTES DO CANCELAMENTO")
    println("Corrotina ativa: ${coroutineJob.isActive}")
    println("Corrotina completa: ${coroutineJob.isCompleted}")
    println("Corrotina cancelada: ${coroutineJob.isCancelled}")

    coroutineJob.cancel()

    println("DEPOIS DO CANCELAMENTO")
    println("Corrotina ativa: ${coroutineJob.isActive}")
    println("Corrotina completa: ${coroutineJob.isCompleted}")
    println("Corrotina cancelada: ${coroutineJob.isCancelled}")

    println("Fim da main")
}

suspend fun getMessage(): String {
    delay(3000)
    return "Hello, world!"
}