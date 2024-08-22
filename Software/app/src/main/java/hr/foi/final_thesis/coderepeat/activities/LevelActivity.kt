package hr.foi.final_thesis.coderepeat.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.adapters.tasks.YesNoAdapter
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.database.LevelDAO
import hr.foi.final_thesis.coderepeat.database.Level_TaskDAO
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.interfaces.tasks.YesNoTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LevelActivity : AppCompatActivity() {
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_level)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }*/
    /*private lateinit var levelTaskDao: Level_TaskDAO
    private lateinit var levelDao: LevelDAO
    private var levelId: Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_level)

        val levelId = intent.getIntExtra("LEVEL_ID", -1)
        if (levelId == -1) {
            Log.e("LevelActivity", "Level ID not provided")
            return
        }
        val db=AppDatabase.getDatabase(this)
        levelDao=db.levelDao()
        levelTaskDao=db.level_taskDao()

        CoroutineScope(Dispatchers.IO).launch {
            val tasks = levelTaskDao.getTasksForLevel(levelId)
            val level = levelDao.getLevelById(levelId)
            Log.i("LevelActivity", "LevelID: ${level?.id}, Level name: ${level?.name}")
            tasks.forEach{task ->
                Log.i("LevelActivity", "TaskID: ${task.id}, Task type: ${task.type} Task CA: ${task.correctAnswer}, Task Q: ${task.question} Task O: ${task.options}")
            }
            /*withContext(Dispatchers.Main) {
            }*/
        }
    }*/
    private lateinit var levelTaskDao: Level_TaskDAO
    private lateinit var levelDao: LevelDAO
    private lateinit var yesNoTaskAdapter: YesNoAdapter
    private var currentTaskIndex: Int = 0
    private var levelId: Int = 0
    private var tasks: List<Task>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_level)

        levelId = intent.getIntExtra("LEVEL_ID", -1)
        if (levelId == -1) {
            Log.e("LevelActivity", "Level ID not provided")
            return
        }
        val db = AppDatabase.getDatabase(this)
        levelDao = db.levelDao()
        levelTaskDao = db.level_taskDao()

        CoroutineScope(Dispatchers.IO).launch {
            val tasks = levelTaskDao.getTasksForLevel(levelId)
            val level = levelDao.getLevelById(levelId)
            Log.i("LevelActivity", "LevelID: ${level?.id}, Level name: ${level?.name}")
            tasks.forEach{task ->
                Log.i("LevelActivity", "TaskID: ${task.id}, Task type: ${task.type} Task CA: ${task.correctAnswer}, Task Q: ${task.question} Task O: ${task.options}")
            }
        }

        yesNoTaskAdapter = YesNoAdapter(YesNoTask(this))

        CoroutineScope(Dispatchers.IO).launch {
            tasks = levelTaskDao.getTasksForLevel(levelId)
            val level = levelDao.getLevelById(levelId)
            Log.i("LevelActivity", "LevelID: ${level?.id}, Level name: ${level?.name}")
            tasks?.forEach { task ->
                Log.i("LevelActivity", "TaskID: ${task.id}, Task type: ${task.type} Task CA: ${task.correctAnswer}, Task Q: ${task.question} Task O: ${task.options}")
            }
            withContext(Dispatchers.Main) {
                if (!tasks.isNullOrEmpty()) {
                    loadTaskFragment(tasks!![currentTaskIndex].id)
                }
            }
        }
    }
    private suspend fun loadTaskFragment(taskId: Int) {
        val task = tasks?.find { it.id == taskId }

        val fragment = when (task?.type) {
            "YES_NO" -> yesNoTaskAdapter.createFragment(taskId)
            else -> {
                Log.e("LevelActivity", "Unknown task type: ${task?.type}. Skipping this task.")
                loadNextTask()
                return
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_level_RL_main_container, fragment)
            .commit()
    }

    suspend fun loadNextTask() {
        currentTaskIndex++
        if (currentTaskIndex < tasks?.size ?: 0) {
            loadTaskFragment(tasks!![currentTaskIndex].id)
        } else {
            // All tasks completed, show summary or end activity
            withContext(Dispatchers.Main) {

                Toast.makeText(this@LevelActivity, "All tasks completed!", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }
}