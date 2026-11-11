import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.ContinuationInterceptor

suspend fun main() {
    CoroutineScope(Dispatchers.IO + CoroutineName("CoroutineFamily")).launch {
        println("Contexto do pai: $coroutineContext")
        println("Thread do pai: ${Thread.currentThread().name}")
        println("Dispatcher do pai: ${coroutineContext[ContinuationInterceptor]}")
    }.join()
}