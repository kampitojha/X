import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

Basic coroutine example
fun main() = runBlocking {
    println("=== Kotlin Coroutines Examples ===\n")
    
    // 1. Basic coroutine launch
    println("1. Basic Coroutine Launch:")
    launch {
        delay(1000L)
        println("Hello from coroutine!")
    }
    println("Main thread continues...")
    delay(1500L)

fun main() = runBlocking {
    println("Hello from main!")
}
    
    // 2. Suspend function example
    println("\n2. Suspend Function:")
    val result = fetchData()
    println("Fetched data: $result")
    
    // 3. Async/Await pattern
    println("\n3. Async/Await Pattern:")
    val time = measureTimeMillis {
        val deferred1 = async { fetchUserData(1) }
        val deferred2 = async { fetchUserData(2) }
        val deferred3 = async { fetchUserData(3) }
        
        val user1 = deferred1.await()
        val user2 = deferred2.await()
        val user3 = deferred3.await()
        
        println("Users: $user1, $user2, $user3")
    }
    println("Time taken: ${time}ms")
    
    // 4. Coroutine scope with lifecycle
    println("\n4. Coroutine Scope:")
    val scope = CoroutineScope(Dispatchers.IO)
    val job = scope.launch {
        repeat(5) { i ->
            println("Working in scope: $i")
            delay(500L)
        }
    }
    delay(2000L)
    job.cancel()
    println("Job cancelled")
    
    // 5. Error handling in coroutines
    println("\n5. Error Handling:")
    try {
        riskyOperation()
    } catch (e: Exception) {
        println("Caught exception: ${e.message}")
    }
    
    // 6. Different dispatchers
    println("\n6. Different Dispatchers:")
    launch(Dispatchers.Default) {
        println("Running on Default dispatcher: ${Thread.currentThread().name}")
    }
    launch(Dispatchers.IO) {
        println("Running on IO dispatcher: ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) {
        println("Running on Unconfined dispatcher: ${Thread.currentThread().name}")
    }
    
    delay(1000L)
    
    // 7. Channel communication
    println("\n7. Channel Communication:")
    val channel = Channel<String>()
    
    launch {
        repeat(3) { i ->
            channel.send("Message $i")
            delay(500L)
        }
        channel.close()
    }
    
    for (message in channel) {
        println("Received: $message")
    }
    
    // 8. Flow example
    println("\n8. Flow Example:")
    val flow = flow {
        repeat(5) { i ->
            emit("Flow value $i")
            delay(300L)
        }
    }
    
    flow.collect { value ->
        println("Flow emitted: $value")
    }
    
    println("\n=== All examples completed! ===")
}

// Suspend function that simulates data fetching
suspend fun fetchData(): String {
    delay(1000L) // Simulate network delay
    return "Data from server"
}

// Suspend function that simulates user data fetching
suspend fun fetchUserData(userId: Int): String {
    delay(1000L) // Simulate network delay
    return "User$userId"
}

// Function that demonstrates error handling
suspend fun riskyOperation() {
    delay(500L)
    if (Math.random() > 0.5) {
        throw Exception("Something went wrong!")
    }
    println("Operation completed successfully")
}

// Extension function to create a simple flow
fun <T> flow(block: suspend FlowCollector<T>.() -> Unit): Flow<T> {
    return kotlinx.coroutines.flow.flow(block)
}

// Flow collector interface
interface FlowCollector<T> {
    suspend fun emit(value: T)
}

// Channel interface
interface Channel<T> {
    suspend fun send(element: T)
    suspend fun receive(): T
    fun close()
    suspend operator fun iterator(): Iterator<T>
}

// Simple channel implementation
class SimpleChannel<T> : Channel<T> {
    private val queue = mutableListOf<T>()
    private var closed = false
    
    override suspend fun send(element: T) {
        if (closed) throw Exception("Channel is closed")
        queue.add(element)
    }
    
    override suspend fun receive(): T {
        while (queue.isEmpty() && !closed) {
            delay(10L)
        }
        if (queue.isEmpty()) throw Exception("Channel is closed and empty")
        return queue.removeAt(0)
    }
    
    override fun close() {
        closed = true
    }
    
    override suspend fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            override fun hasNext(): Boolean = !queue.isEmpty() || !closed
            
            override fun next(): T = receive()
        }
    }
}

// Coroutine scope interface
interface CoroutineScope {
    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job
}

// Job interface
interface Job {
    fun cancel()
}

// Coroutine context and dispatchers
object Dispatchers {
    object Default : CoroutineContext
    object IO : CoroutineContext
    object Unconfined : CoroutineContext
}

// Coroutine context interface
interface CoroutineContext

// Coroutine start enum
enum class CoroutineStart {
    DEFAULT
}

// Empty coroutine context
object EmptyCoroutineContext : CoroutineContext

// Async interface
interface Deferred<T> {
    suspend fun await(): T
}

// Global scope implementation
object GlobalScope : CoroutineScope {
    override fun launch(
        context: CoroutineContext,
        start: CoroutineStart,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return SimpleJob()
    }
}

// Simple job implementation
class SimpleJob : Job {
    private var cancelled = false
    
    override fun cancel() {
        cancelled = true
    }
}

// Simple async implementation
fun <T> async(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): Deferred<T> {
    return SimpleDeferred(block)
}

// Simple deferred implementation
class SimpleDeferred<T>(private val block: suspend CoroutineScope.() -> T) : Deferred<T> {
    private var result: T? = null
    private var completed = false
    
    override suspend fun await(): T {
        if (!completed) {
            result = block(GlobalScope)
            completed = true
        }
        return result!!
    }
}

// Run blocking implementation
fun <T> runBlocking(context: CoroutineContext = EmptyCoroutineContext, block: suspend CoroutineScope.() -> T): T {
    return block(GlobalScope)
}

// Launch function
fun launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return GlobalScope.launch(context, start, block)
}

// Delay function
suspend fun delay(timeMillis: Long) {
    Thread.sleep(timeMillis)
}

// Measure time function
fun measureTimeMillis(block: () -> Unit): Long {
    val start = System.currentTimeMillis()
    block()
    return System.currentTimeMillis() - start
}
