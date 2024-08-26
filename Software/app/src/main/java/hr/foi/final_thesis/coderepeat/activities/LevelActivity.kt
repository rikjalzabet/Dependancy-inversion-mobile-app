package hr.foi.final_thesis.coderepeat.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.adapters.tasks.FillTheBlankAdapter
import hr.foi.final_thesis.coderepeat.adapters.tasks.MatchTheAnswersAdapter
import hr.foi.final_thesis.coderepeat.adapters.tasks.MultipleChoiceAdapter
import hr.foi.final_thesis.coderepeat.adapters.tasks.MultipleChoiceSingleCorrectAdapter
import hr.foi.final_thesis.coderepeat.adapters.tasks.YesNoAdapter
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.database.LevelDAO
import hr.foi.final_thesis.coderepeat.database.Level_TaskDAO
import hr.foi.final_thesis.coderepeat.database.TaskDAO
import hr.foi.final_thesis.coderepeat.database.Task_UserAnswerDAO
import hr.foi.final_thesis.coderepeat.database.UserAnswerDAO
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Level_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Level_Task_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Task_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Task_UserAnswer_intf_impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.UserAnswer_intf_impl
import hr.foi.final_thesis.coderepeat.interfaces.tasks.FillTheBlankTask
import hr.foi.final_thesis.coderepeat.interfaces.tasks.MatchTheAnswersTask
import hr.foi.final_thesis.coderepeat.interfaces.tasks.MultipleChoiceSingleChoiceTask
import hr.foi.final_thesis.coderepeat.interfaces.tasks.MultipleChoiceTask
import hr.foi.final_thesis.coderepeat.interfaces.tasks.YesNoTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LevelActivity : AppCompatActivity() {
    private lateinit var levelTaskDao: Level_TaskDAO
    private lateinit var levelDao: LevelDAO
    private lateinit var taskDao: TaskDAO
    private lateinit var taskUserAnswer: Task_UserAnswerDAO
    private lateinit var userAnswer: UserAnswerDAO
    private lateinit var yesNoTaskAdapter: YesNoAdapter
    private lateinit var multipleChoiceSingleCorrectTaskAdapter: MultipleChoiceSingleCorrectAdapter
    private lateinit var multipleChoiceMultipleCorrectTaskAdapter: MultipleChoiceAdapter
    private lateinit var fillTheBlankTaskAdapter: FillTheBlankAdapter
    private lateinit var matchTheAnswerTaskAdapter: MatchTheAnswersAdapter

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
        taskDao = db.taskDao()
        taskUserAnswer = db.task_userAnswerDao()
        userAnswer = db.userAnswerDao()

        CoroutineScope(Dispatchers.IO).launch {
            val tasks = levelTaskDao.getTasksForLevel(levelId)
            val level = levelDao.getLevelById(levelId)
            Log.i("LevelActivity", "LevelID: ${level?.id}, Level name: ${level?.name}")
            tasks.forEach{task ->
                Log.i("LevelActivity", "TaskID: ${task.id}, Task type: ${task.type} Task CA: ${task.correctAnswer}, Task Q: ${task.question} Task O: ${task.options}")
            }
        }

        yesNoTaskAdapter = YesNoAdapter(YesNoTask(
            Level_Intf_Impl(levelDao),
            Task_Intf_Impl(taskDao),
            Level_Task_Intf_Impl(levelTaskDao),
            Task_UserAnswer_intf_impl(taskUserAnswer),
            UserAnswer_intf_impl(userAnswer)
        ))
        multipleChoiceSingleCorrectTaskAdapter = MultipleChoiceSingleCorrectAdapter(MultipleChoiceSingleChoiceTask(
            Level_Intf_Impl(levelDao),
            Task_Intf_Impl(taskDao),
            Level_Task_Intf_Impl(levelTaskDao),
            Task_UserAnswer_intf_impl(taskUserAnswer),
            UserAnswer_intf_impl(userAnswer)
        ))
        multipleChoiceMultipleCorrectTaskAdapter = MultipleChoiceAdapter(MultipleChoiceTask(
            Level_Intf_Impl(levelDao),
            Task_Intf_Impl(taskDao),
            Level_Task_Intf_Impl(levelTaskDao),
            Task_UserAnswer_intf_impl(taskUserAnswer),
            UserAnswer_intf_impl(userAnswer)
        ))
        fillTheBlankTaskAdapter = FillTheBlankAdapter(FillTheBlankTask(
            Level_Intf_Impl(levelDao),
            Task_Intf_Impl(taskDao),
            Level_Task_Intf_Impl(levelTaskDao),
            Task_UserAnswer_intf_impl(taskUserAnswer),
            UserAnswer_intf_impl(userAnswer)
        ))
        matchTheAnswerTaskAdapter = MatchTheAnswersAdapter(MatchTheAnswersTask(
            Level_Intf_Impl(levelDao),
            Task_Intf_Impl(taskDao),
            Level_Task_Intf_Impl(levelTaskDao),
            Task_UserAnswer_intf_impl(taskUserAnswer),
            UserAnswer_intf_impl(userAnswer)
        ))

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
            "YES_NO" -> yesNoTaskAdapter.createFragment(taskId, currentTaskIndex, tasks!!.size)
            "MULTIPLE_CHOICE_SINGLE_ANSWER" -> multipleChoiceSingleCorrectTaskAdapter.createFragment(taskId, currentTaskIndex, tasks!!.size)
            "MULTIPLE_CHOICE_MULTIPLE_ANSWERS" -> multipleChoiceMultipleCorrectTaskAdapter.createFragment(taskId, currentTaskIndex, tasks!!.size)
            "FILL_IN_THE_BLANK" -> fillTheBlankTaskAdapter.createFragment(taskId, currentTaskIndex, tasks!!.size)
            "MATCH_THE_ANSWERS" -> matchTheAnswerTaskAdapter.createFragment(taskId, currentTaskIndex, tasks!!.size)
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
            withContext(Dispatchers.Main) {
                Toast.makeText(this@LevelActivity, "All tasks completed!", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }
    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Exit Level")
            .setMessage("Do you want to exit the current level? By exiting now your current progress will NOT be saved!")
            .setPositiveButton("Yes") { dialog, which ->
                super.onBackPressed()
            }
            .setNegativeButton("No", null)
            .show()
    }
}