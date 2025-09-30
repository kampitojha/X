// Android me Material UI ka use karne ke liye aap "Material Components" library ka use karte hain.
// Neeche ek simple example diya hai jisme Material Button aur Material TextField ka use kiya gaya hai.

// 1. build.gradle (app level) me dependency add karo:
// implementation 'com.google.android.material:material:1.9.0'

// 2. XML layout file (res/layout/activity_main.xml) me Material Components ka use:

/*
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Email">

        <com.google.android.material.textfield.TextInputEditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>
    </com.google.android.material.textfield.TextInputLayout>

    <com.google.android.material.button.MaterialButton
        android:id="@+id/materialButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Material Button"
        style="@style/Widget.MaterialComponents.Button"/>
</LinearLayout>
*/

// 3. Kotlin code (MainActivity.kt) me Material Button ka use:

/*
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val materialButton = findViewById<MaterialButton>(R.id.materialButton)
        materialButton.setOnClickListener {
            Toast.makeText(this, "Material Button Clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}
*/

// Material UI ka mtlb hai ki aapke app ka look modern aur Google ke Material Design guidelines ke according ho.
// Material Components me aur bhi widgets milte hain jaise CardView, Chip, BottomNavigation, Snackbar, etc.

// Official docs: https://material.io/develop/android
