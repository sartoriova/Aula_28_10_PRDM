import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() {
    val coroutine = CoroutineScope(Dispatchers.Default).launch {
        println(getMessage())
    }
    //coroutine.join()

    println("ANTES DO CANCELAMENTO")
    println("Corrotina ativa: ${coroutine.isActive}")
    println("Corrotina completa: ${coroutine.isCompleted}")
    println("Corrotina cancelada: ${coroutine.isCancelled}")

    coroutine.cancel()

    println("DEPOIS DO CANCELAMENTO")
    println("Corrotina ativa: ${coroutine.isActive}")
    println("Corrotina completa: ${coroutine.isCompleted}")
    println("Corrotina cancelada: ${coroutine.isCancelled}")

    println("Fim da main")
}

suspend fun getMessage(): String {
    delay(3000)
    return "Hello, world!"
}