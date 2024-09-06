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
import hr.foi.final_thesis.coderepeat.database.StreakDAO
import hr.foi.final_thesis.coderepeat.database.TaskDAO
import hr.foi.final_thesis.coderepeat.database.Task_UserAnswerDAO
import hr.foi.final_thesis.coderepeat.database.UserAnswerDAO
import hr.foi.final_thesis.coderepeat.entities.Level_Task
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.entities.UserAnswer
import hr.foi.final_thesis.coderepeat.helpers.DateConverter
import hr.foi.final_thesis.coderepeat.interfaces.ILevel_Task
import hr.foi.final_thesis.coderepeat.interfaces.ISection
import hr.foi.final_thesis.coderepeat.interfaces.ISection_Level
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Level_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Level_Task_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Streak_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Task_UserAnswer_intf_impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.UserAnswer_intf_impl
import hr.foi.final_thesis.coderepeat.interfaces.tasks.ITaskHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class LevelSummaryActivity(): AppCompatActivity() {
    private lateinit var levelTaskDao: Level_TaskDAO
    private lateinit var taskUserAnswer: Task_UserAnswerDAO
    private lateinit var userAnswer: UserAnswerDAO
    private lateinit var streakDao: StreakDAO
    private lateinit var levelDao: LevelDAO

    private lateinit var taskUserAnswerImpl: Task_UserAnswer_intf_impl
    private lateinit var userAnswerImpl: UserAnswer_intf_impl
    private lateinit var levelTaskImpl: Level_Task_Intf_Impl
    private lateinit var streakIntfImpl: Streak_Intf_Impl
    private lateinit var levelIntfImpl: Level_Intf_Impl

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
        streakDao = db.streakDao()
        levelDao= db.levelDao()

        taskUserAnswerImpl = Task_UserAnswer_intf_impl(taskUserAnswer)
        userAnswerImpl = UserAnswer_intf_impl(userAnswer)
        levelTaskImpl = Level_Task_Intf_Impl(levelTaskDao)
        streakIntfImpl= Streak_Intf_Impl(streakDao)
        levelIntfImpl= Level_Intf_Impl(levelDao)

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

            var score = 0
            val currentLevel = levelIntfImpl.getLevelById(levelId)
            val tasksForCurrentLevel = levelTaskImpl.getTasksForLevel(levelId)
            for (task in tasksForCurrentLevel) {
                val LevelTaskPoints = levelTaskImpl.getPointsForLevelTask(levelId, task.id)
                if (LevelTaskPoints>0) {
                    score++
                }
            }
            if(score > currentLevel?.score!!){
                currentLevel?.score = score
                levelIntfImpl.updateLevel(currentLevel!!)
                for (task in tasksForCurrentLevel) {
                    levelTaskImpl.updatePointsLevel_Task(levelId, task.id,0.0)
                }
            }else{
                Log.i("LevelSumAct","Score is not higher than the current score")
            }

        }
        recyclerView.layoutManager = LinearLayoutManager(this)

        closeTheSummary.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                taskHandler.deleteAllUserAnswers()
                taskHandler.deleteAllTask_UserAnswers()

                val streak = streakIntfImpl.getAllStreaks()[0]
                val currentDate = LocalDate.now()
                val lastActiveDate = if (streak.lastActiveDate.isNotEmpty()) LocalDate.parse(streak.lastActiveDate) else null
                Log.i("LevelSumAct","CURRENT DATE: $currentDate and LAST ACTIVE DATE: $lastActiveDate")

                if(lastActiveDate != currentDate){
                    if (lastActiveDate == null) {
                        val updatedStreak = streak.copy(
                            currentStreak = 1,
                            startDate = DateConverter().convertLocalDateToString(currentDate),
                            lastActiveDate = DateConverter().convertLocalDateToString(currentDate)
                        )
                        Log.i("LevelSumAct","STREAK FIRST DAY NEW: $updatedStreak")
                        streakIntfImpl.updateStreak(updatedStreak)
                    }else{
                        val daysBetween = DateConverter().subtractDates(lastActiveDate, currentDate).toInt()
                        Log.i("LevelSumAct","DAYS BETWEEN: $daysBetween")
                        if (daysBetween == 1) {
                            val updatedStreak = streak.copy(
                                currentStreak = streak.currentStreak + 1,
                                lastActiveDate = currentDate.toString()
                            )
                            streakIntfImpl.updateStreak(updatedStreak)
                            Log.i("LevelSumAct","STREAK UPDATE: $updatedStreak")
                        }else {// if (daysBetween > 1L)
                            val updatedStreak = streak.copy(
                                currentStreak = 1,
                                startDate = currentDate.toString(),
                                lastActiveDate = currentDate.toString()
                            )
                            Log.i("LevelSumAct","STREAK FULL RESET: $updatedStreak")
                            streakIntfImpl.updateStreak(updatedStreak)
                        }
                    }
                }
                else{
                    Log.i("LevelSumAct","STREAK WAS ALREADY UPDATED TODAY")
                }
            }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
        }
    }
}