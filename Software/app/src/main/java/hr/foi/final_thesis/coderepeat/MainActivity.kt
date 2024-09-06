package hr.foi.final_thesis.coderepeat

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import hr.foi.final_thesis.coderepeat.database.DatabaseManager
import hr.foi.final_thesis.coderepeat.fragments.ListSectionFragment
import hr.foi.final_thesis.coderepeat.fragments.StreakFragment
import hr.foi.final_thesis.coderepeat.interfaces.navigation.AppNavigation
import hr.foi.final_thesis.coderepeat.interfaces.navigation.AppNavigation_Intf_Impl

class MainActivity : AppCompatActivity() {
    private lateinit var appNavigation: AppNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val bottomNavigationBar: BottomNavigationView=findViewById(R.id.activity_main_BNV_bottom_navigation)

        //appNavigation=(applicationContext as ApplicationMain).serviceLocator.provideNavigator(this)
        val appNavigationImpl = AppNavigation_Intf_Impl(
            this,
            listOf(
                ListSectionFragment(),
                StreakFragment()
            )
        )
        appNavigation = (applicationContext as ApplicationMain).getServiceLocator(appNavigationImpl).provideNavigator()

        if(savedInstanceState==null){
            appNavigation.navigateToHome()
        }

        bottomNavigationBar.setOnItemSelectedListener{ menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    appNavigation.navigateToHome()
                }
                R.id.nav_streak -> {
                    appNavigation.navigateToStreak()
                }
            }
            true
        }

        DatabaseManager.initializeDatabase(this) {
            Log.d("Database", "Database initialized")
            appNavigation.navigateToHome()
        }
    }
}
