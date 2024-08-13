package hr.foi.final_thesis.coderepeat

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import hr.foi.final_thesis.coderepeat.database.DatabaseManager
import hr.foi.final_thesis.coderepeat.interfaces.navigation.AppNavigation

class MainActivity : AppCompatActivity() {
    //private lateinit var frameLayout: FrameLayout
   // private lateinit var selectedFragment: Fragment
   // private val fragmentList = ArrayList<Fragment>()
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
        //frameLayout = findViewById(R.id.activity_main_FL_main_container)

        //fragmentList.add(ListSectionFragment())
        //appNavigation = AppNavigation_Intf_Impl(this, fragmentList)

        //selectedFragment = fragmentList[0]
        //appNavigation.navigateToHome()

        //fragmentsupportManager()

        bottomNavigationBar.setOnItemSelectedListener{ menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    //selectedFragment = fragmentList[0]
                    appNavigation.navigateToHome()
                }
                R.id.nav_Test->{
                    //selectedFragment = fragmentList[1]
                    appNavigation.navigateToTest()
                }

            }
            //fragmentsupportManager()
            true
        }

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        DatabaseManager.initializeDatabase(this) {
            Log.d("Database", "Database initialized")
            //fragmentsupportManager()
            appNavigation.navigateToHome()
        }
    }
    /*private fun fragmentsupportManager(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_main_FL_main_container, selectedFragment)
            .commit()
    }*/
}
