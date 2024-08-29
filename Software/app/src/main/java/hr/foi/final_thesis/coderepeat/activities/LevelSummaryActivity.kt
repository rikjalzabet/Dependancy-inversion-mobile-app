package hr.foi.final_thesis.coderepeat.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foi.final_thesis.coderepeat.MainActivity
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.adapters.LevelSummaryAdapter
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.database.LevelDAO
import hr.foi.final_thesis.coderepeat.database.Level_TaskDAO
import hr.foi.final_thesis.coderepeat.database.SectionDAO
import hr.foi.final_thesis.coderepeat.database.Section_LevelDAO
import hr.foi.final_thesis.coderepeat.database.TaskDAO
import hr.foi.final_thesis.coderepeat.database.Task_UserAnswerDAO
import hr.foi.final_thesis.coderepeat.database.UserAnswerDAO
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.entities.UserAnswer
import hr.foi.final_thesis.coderepeat.interfaces.ILevel_Task
import hr.foi.final_thesis.coderepeat.interfaces.ISection
import hr.foi.final_thesis.coderepeat.interfaces.ISection_Level
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Level_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Level_Task_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Task_UserAnswer_intf_impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.UserAnswer_intf_impl
import hr.foi.final_thesis.coderepeat.interfaces.tasks.ITaskHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LevelSummaryActivity(): AppCompatActivity() {
    private lateinit var levelTaskDao: Level_TaskDAO
    private lateinit var taskUserAnswer: Task_UserAnswerDAO
    private lateinit var userAnswer: UserAnswerDAO

    private lateinit var taskUserAnswerImpl: Task_UserAnswer_intf_impl
    private lateinit var userAnswerImpl: UserAnswer_intf_impl
    private lateinit var levelTaskImpl: Level_Task_Intf_Impl

    private lateinit var recyclerView: RecyclerView
    private lateinit var closeTheSummary: Button

    val db = AppDatabase.getDatabase(this)

    companion object {
        lateinit var taskHandler: ITaskHandler
        var levelId: Int=0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_level_summary)
        Log.i("LevelSumAct","Here i am")
        recyclerView= findViewById(R.id.activity_level_summary_rv_summary_data)
        closeTheSummary=findViewById(R.id.activity_level_summary_btn_done)
        levelTaskDao = db.level_taskDao()
        taskUserAnswer = db.task_userAnswerDao()
        userAnswer = db.userAnswerDao()
        taskUserAnswerImpl = Task_UserAnswer_intf_impl(taskUserAnswer)
        userAnswerImpl = UserAnswer_intf_impl(userAnswer)
        levelTaskImpl = Level_Task_Intf_Impl(levelTaskDao)
        
            Log.i("LevelSumAct","Here i am 2")
            Log.d("LevelSummaryActivity", "Level ID: $levelId")
        CoroutineScope(Dispatchers.IO).launch {
            val taskList=levelTaskImpl.getTasksForLevel(levelId)
            val userAnswersForTasks = taskList.map { task ->
                val userAnswers = taskUserAnswerImpl.getAnswerForTask(task.id)
                task to userAnswers
            }

            withContext(Dispatchers.Main) {
                val adapter = LevelSummaryAdapter(levelId, userAnswerImpl, levelTaskImpl, taskUserAnswerImpl, taskList, userAnswersForTasks)
                recyclerView.adapter = adapter
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this)


        closeTheSummary.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                Log.i("ALLUSERANSWERS: ", "BEFORE DELETE2: ${taskUserAnswer.getAllTask_UserAnswers()}")
                taskHandler.deleteAllUserAnswers()
                taskHandler.deleteAllTask_UserAnswers()
                Log.i("LevelSumAct","DELETED DATA USERANSWER")
                Log.i("ALLUSERANSWERS: ", "AFTER DELETE: ${taskUserAnswer.getAllTask_UserAnswers()}")
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}