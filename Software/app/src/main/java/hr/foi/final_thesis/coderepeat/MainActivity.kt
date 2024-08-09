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

    private lateinit var level: ILevel
    private lateinit var task: ITask
    private lateinit var section: ISection
    private lateinit var Level_Task: ILevel_Task
    private lateinit var Section_Level: ISection_Level

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val bottomNavigationBar: BottomNavigationView=findViewById(R.id.activity_main_BNV_bottom_navigation)

        frameLayout = findViewById(R.id.activity_main_FL_main_container)

        fragmentList.add(ListSectionFragment())
        selectedFragment = fragmentList[0]
        fragmentsupportManager()

       /* bottomNavigationBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_main_BNV_bottom_navigation, ListSectionFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }*/
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
        }
        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activity_main_BNV_bottom_navigation, ListSectionFragment())
                .commit()
        }*/

        CoroutineScope(Dispatchers.IO).launch {
            level = Level_Intf_Impl(this@MainActivity)
            task = Task_Intf_Impl(this@MainActivity)
            section = Section_Intf_Impl(this@MainActivity)
            Level_Task = Level_Task_Intf_Impl(this@MainActivity)
            Section_Level = Section_Level_Intf_Impl(this@MainActivity)
            val levels = level.getAllLevels()
            val tasks = task.getAllTasks()
            val sections = section.getAllSections()

            if(levels.isEmpty() && tasks.isEmpty() && sections.isEmpty())
                populateData(this@MainActivity)


            /*if(levels.isEmpty() && tasks.isEmpty() && sections.isEmpty()) {
                Log.d("ARE THEY EMPTY - YES","${levels.isEmpty()} | ${tasks.isEmpty()} | ${sections.isEmpty()}")
                populateData(this@MainActivity)
                Log.d("LEVELS-add", levels.toString())
                Log.d("TASKS-add", tasks.toString())
                Log.d("SECTIONS-add", sections.toString())
            }
            Log.d("ARE THEY EMPTY?","${levels.isEmpty()} | ${tasks.isEmpty()} | ${sections.isEmpty()}")

            if(!levels.isEmpty() && !tasks.isEmpty() && !sections.isEmpty()) {
                Log.d("ARE THEY EMPTY - NO BUT I DELETE IT","$levels | $tasks | $sections")

                // Delete connections in n:n tables first
                for (level_task in Level_Task.getAllLevel_Tasks()) {
                    Level_Task.deleteLevel_Task(level_task.levelId, level_task.taskId)
                }
                for (section_level in Section_Level.getAllSection_Levels()) {
                    Section_Level.deleteSection_Level(section_level.sectionId, section_level.levelId)
                }

                for (lvl in levels) {
                    level.deleteLevel(lvl)
                }
                for (tsk in tasks) {
                    task.deleteTask(tsk)
                }
                for (sect in sections) {
                    section.deleteSection(sect)
                }

                Log.d("LEVELS-del", levels.toString())
                Log.d("TASKS-del", tasks.toString())
                Log.d("SECTIONS-del", sections.toString())
            }*/


            Log.d("LEVELS-done", levels.toString())
            Log.d("TASKS-done", tasks.toString())
            Log.d("SECTIONS-done", sections.toString())
        }
    }
    private fun fragmentsupportManager(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_main_FL_main_container, selectedFragment)
            .commit()
    }
}
