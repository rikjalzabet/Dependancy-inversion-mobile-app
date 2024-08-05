package hr.foi.final_thesis.coderepeat

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import hr.foi.final_thesis.coderepeat.helpers.populateData
import hr.foi.final_thesis.coderepeat.repository.LevelRepository
import hr.foi.final_thesis.coderepeat.repository.StreakRepository
import hr.foi.final_thesis.coderepeat.repository.TaskRepository
import hr.foi.final_thesis.coderepeat.repository.implementation.LevelRepositoryImpl
import hr.foi.final_thesis.coderepeat.repository.implementation.StreakRepositoryImpl
import hr.foi.final_thesis.coderepeat.repository.implementation.TaskRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var levelRepository: LevelRepository
    private lateinit var taskRepository: TaskRepository
    private lateinit var streakRepository: StreakRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

/*
        CoroutineScope(Dispatchers.IO).launch {
            levelRepository = LevelRepositoryImpl(this@MainActivity)
            taskRepository = TaskRepositoryImpl(this@MainActivity)
            streakRepository = StreakRepositoryImpl(this@MainActivity)

            val levels = levelRepository.getAllLevels()
            val tasks = taskRepository.getAllTasks()
            val streak = streakRepository.getAllStreaks()

            if(levels.isEmpty() && tasks.isEmpty() && streak.isEmpty()) {
                Log.d("ARE THEY EMPTY - YES","${levels.isEmpty()} | ${tasks.isEmpty()} | ${streak.isEmpty()}")
                populateData(this@MainActivity)
                Log.d("LEVELS-add", levels.toString())
                Log.d("TASKS-add", tasks.toString())
                Log.d("STREAK-add", streak.toString())
            }
            Log.d("ARE THEY EMPTY?","${levels.isEmpty()} | ${tasks.isEmpty()} | ${streak.isEmpty()}")

            if(!levels.isEmpty() && !tasks.isEmpty() && !streak.isEmpty()) {
                Log.d("ARE THEY EMPTY - NO BUT I DELETE IT","$levels | $tasks | $streak")
                for (level in levels) {
                    levelRepository.deleteLevel(level)
                }
                for (task in taskRepository.getAllTasks()) {
                    taskRepository.deleteTask(task)
                }
                for (task in streakRepository.getAllStreaks()) {
                    streakRepository.deleteStreak(task)
                }
                Log.d("LEVELS-del", levels.toString())
                Log.d("TASKS-del", tasks.toString())
                Log.d("STREAK-del", streak.toString())
            }
            Log.d("LEVELS-done", levels.toString())
            Log.d("TASKS-done", tasks.toString())
            Log.d("STREAK-done", streak.toString())
        }
*/


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
