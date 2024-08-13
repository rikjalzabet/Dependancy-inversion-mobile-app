package hr.foi.final_thesis.coderepeat

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import hr.foi.final_thesis.coderepeat.database.DatabaseManager
import hr.foi.final_thesis.coderepeat.interfaces.navigation.AppNavigation

class MainActivity : AppCompatActivity() {
    private lateinit var appNavigation: AppNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val bottomNavigationBar: BottomNavigationView=findViewById(R.id.activity_main_BNV_bottom_navigation)

        appNavigation=(applicationContext as ApplicationMain).serviceLocator.provideNavigator(this)
        if(savedInstanceState==null){
            appNavigation.navigateToHome()
        }

        bottomNavigationBar.setOnItemSelectedListener{ menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    appNavigation.navigateToHome()
                }
            }
            true
        }

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        DatabaseManager.initializeDatabase(this) {
            Log.d("Database", "Database initialized")
            appNavigation.navigateToHome()
        }
    }
}
