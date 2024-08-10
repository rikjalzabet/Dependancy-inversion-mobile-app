package hr.foi.final_thesis.coderepeat.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.database.LevelDAO
import hr.foi.final_thesis.coderepeat.database.Level_TaskDAO
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
    private lateinit var levelTaskDao: Level_TaskDAO
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
    }

}