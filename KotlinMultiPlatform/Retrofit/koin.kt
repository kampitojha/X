// Koin ek dependency injection library hai jo Kotlin/Android me use hoti hai.
// Yahan basic example diya hai ki Retrofit service ko Koin ke through kaise provide karte hain:

import org.koin.dsl.module
import org.koin.core.context.startKoin
import org.koin.core.module.Module

// 1. Koin module define karo jisme dependencies provide karni hain
val appModule: Module = module {
    // Retrofit service ko singleton ke roop me provide kar rahe hain
    single { createRetrofitService() }
}

// 2. Koin ko start karo apne application ke entry point pe
fun main() {
    // Koin start karte hain aur module pass karte hain
    startKoin {
        modules(appModule)
    }

    // Ab ApiService ko Koin se inject kar sakte hain
    // Example: get<ApiService>() (yeh tab kaam karega jab aap Koin ke context me ho)
}

// Note: Android ya multiplatform project me Koin ka setup thoda alag ho sakta hai,
// lekin yeh basic idea hai ki dependencies ko module me define karo aur Koin start karo.



// Koin se dependency inject karne ka ek pure example niche diya hai:

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

// ApiService ko inject karne ke liye ek class banate hain
class UserRepository : KoinComponent {
    // ApiService ko inject kar rahe hain
    private val apiService: ApiService by inject()

    // Users fetch karne ka function
    suspend fun printUsers() {
        try {
            val users = apiService.getUsers()
            users.forEach { user ->
                println("User: ${user.name}, Email: ${user.email}")
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
}

// Main function me Koin start karne ke baad UserRepository ka use karte hain
import kotlinx.coroutines.runBlocking

fun main() {
    // Koin start karte hain aur module pass karte hain
    startKoin {
        modules(appModule)
    }

    // UserRepository ka instance banate hain (KoinComponent hai to inject ho jayega)
    val userRepository = UserRepository()

    // Coroutine ke andar users print karte hain
    runBlocking {
        userRepository.printUsers()
    }
}
