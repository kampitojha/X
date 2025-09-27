// Retrofit ka use karke ek simple API call ka example de raha hoon, aur har ek cheez pe comment likh raha hoon ki kya ho raha hai.

// Retrofit dependencies chahiye hoti hain, lekin yahan sirf code likh rahe hain.

// 1. Data class banate hain jo API response ko represent karega
data class User(
    val id: Int,
    val name: String,
    val email: String
)

// 2. Retrofit interface banate hain jisme API endpoints define karte hain
interface ApiService {
    // Yahan pe ek GET request define ki hai jo users list laayega
    // "users" endpoint pe call jayegi aur List<User> return karegi
    @GET("users")
    suspend fun getUsers(): List<User>
}

// 3. Retrofit instance create karte hain
fun createRetrofitService(): ApiService {
    // Yahan pe Retrofit builder use ho raha hai
    // Base URL set kar rahe hain (yeh example ke liye placeholder hai)
    val retrofit = Retrofit.Builder()
        .baseUrl("https://example.com/api/") // yahan apni API ka base URL daalein
        .addConverterFactory(GsonConverterFactory.create()) // JSON ko object me convert karega
        .build()

    // ApiService ka implementation return ho raha hai
    return retrofit.create(ApiService::class.java)
}

// 4. API call ko coroutine ke andar chalate hain
suspend fun fetchAndPrintUsers() {
    // Retrofit service create kar rahe hain
    val apiService = createRetrofitService()
    try {
        // API se users fetch kar rahe hain
        val users = apiService.getUsers()
        // Har user ko print kar rahe hain
        users.forEach { user ->
            println("User: ${user.name}, Email: ${user.email}")
        }
    } catch (e: Exception) {
        // Agar koi error aata hai toh yahan handle kar rahe hain
        println("Error: ${e.message}")
    }
}

// 5. Main function jahan se program start hoga
fun main() = runBlocking {
    // fetchAndPrintUsers ko call kar rahe hain
    fetchAndPrintUsers()
}

// smjhao kse kya ho rha har ek chih:
// - Data class User: API se aane wale user data ko hold karta hai
// - ApiService: Retrofit interface hai, yahan API endpoints define hote hain
// - createRetrofitService: Retrofit ka instance banata hai, jo API calls karne ke liye use hota hai
// - fetchAndPrintUsers: API se data fetch karta hai aur print karta hai, error handle karta hai
// - main: Program ka entry point hai, yahan se sab start hota hai
