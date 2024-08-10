package hr.foi.final_thesis.coderepeat

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import hr.foi.final_thesis.coderepeat.adapters.SectionAdapter
import hr.foi.final_thesis.coderepeat.database.DatabaseManager
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Level_Task
import hr.foi.final_thesis.coderepeat.entities.Section_Level
import hr.foi.final_thesis.coderepeat.fragments.ListSectionFragment
import hr.foi.final_thesis.coderepeat.interfaces.ILevel
import hr.foi.final_thesis.coderepeat.interfaces.ILevel_Task
import hr.foi.final_thesis.coderepeat.interfaces.ISection
import hr.foi.final_thesis.coderepeat.interfaces.ISection_Level
import hr.foi.final_thesis.coderepeat.interfaces.IStreak
import hr.foi.final_thesis.coderepeat.interfaces.ITask
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Level_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Level_Task_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Section_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Section_Level_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Task_Intf_Impl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import populateData
import kotlin.text.Typography.section

class MainActivity : AppCompatActivity() {
    private lateinit var frameLayout: FrameLayout
    private lateinit var selectedFragment: Fragment
    private val fragmentList = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val bottomNavigationBar: BottomNavigationView=findViewById(R.id.activity_main_BNV_bottom_navigation)

        frameLayout = findViewById(R.id.activity_main_FL_main_container)

        fragmentList.add(ListSectionFragment())
        selectedFragment = fragmentList[0]
        fragmentsupportManager()

        bottomNavigationBar.setOnItemSelectedListener{ menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    selectedFragment = fragmentList[0]
                }

            }
            fragmentsupportManager()
            true
        }

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        DatabaseManager.initializeDatabase(this) {
            Log.d("Database", "Database initialized")
            fragmentsupportManager()
        }
    }
    private fun fragmentsupportManager(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_main_FL_main_container, selectedFragment)
            .commit()
    }
}
