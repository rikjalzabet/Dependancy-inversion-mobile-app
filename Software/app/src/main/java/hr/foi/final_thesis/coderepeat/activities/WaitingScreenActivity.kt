package hr.foi.final_thesis.coderepeat.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import hr.foi.final_thesis.coderepeat.MainActivity
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.database.DatabaseManager

class WaitingScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting_screen)
        DatabaseManager.initializeDatabase(this) {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 2500)
        }
    }
}