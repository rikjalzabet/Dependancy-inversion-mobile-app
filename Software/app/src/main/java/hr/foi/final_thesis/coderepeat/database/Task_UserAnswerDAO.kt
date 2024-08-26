package hr.foi.final_thesis.coderepeat.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Section
import hr.foi.final_thesis.coderepeat.entities.Section_Level
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.entities.Task_UserAnswer
import hr.foi.final_thesis.coderepeat.entities.UserAnswer

@Dao
interface Task_UserAnswerDAO {
    @Query("SELECT * FROM Task WHERE id IN (SELECT taskId FROM Task_UserAnswer WHERE answerId = :answerId)")
    fun getTaskForAnswer(answerId: Int): List<Task>
    @Query("SELECT * FROM UserAnswers WHERE id IN (SELECT answerId FROM Task_UserAnswer WHERE taskId = :taskId)")
    fun getAnswerForTask(taskId: Int): List<UserAnswer>
    @Query("SELECT * FROM Task_UserAnswer")
    fun getAllTask_UserAnswers(): List<Task_UserAnswer>
    @Query("SELECT * FROM UserAnswers WHERE id IN (SELECT answerId FROM Task_UserAnswer WHERE taskId = :taskId)")
    fun getUserAnswerForTask(taskId: Int): List<UserAnswer>
    @Query("DELETE FROM Task_UserAnswer")
    fun deleteAllTask_UserAnswers()
    @Insert
    fun insertTask_UserAnswer(task_userAnswer: Task_UserAnswer): Long
    @Update
    fun updateTask_UserAnswer(task_userAnswer: Task_UserAnswer)
    @Delete
    fun deleteTask_UserAnswer(task_userAnswer: Task_UserAnswer)
    @Query("DELETE FROM Task_UserAnswer WHERE taskId = :taskId AND answerId = :answerId")
    fun deleteTask_UserAnswer(taskId: Int, answerId: Int)
}