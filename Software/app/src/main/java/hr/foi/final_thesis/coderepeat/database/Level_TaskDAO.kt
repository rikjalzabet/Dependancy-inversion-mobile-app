package hr.foi.final_thesis.coderepeat.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Level_Task
import hr.foi.final_thesis.coderepeat.entities.Task

@Dao
interface Level_TaskDAO {
    @Query("SELECT * FROM Task WHERE id IN (SELECT taskId FROM Level_Task WHERE levelId = :levelId)")
    fun getTasksForLevel(levelId: Int): List<Task>
    @Query("SELECT * FROM Level WHERE id IN (SELECT levelId FROM Level_Task WHERE taskId = :taskId)")
    fun getLevelsForTask(taskId: Int): List<Level>
    @Query("SELECT * FROM Level_Task")
    fun getAllLevel_Tasks(): List<Level_Task>
    @Query("SELECT points FROM Level_Task WHERE levelId = :levelId AND taskId = :taskId")
    fun getPointsForLevelTask(levelId: Int, taskId: Int): Double
    @Insert
    fun insertLevel_Task(level_task: Level_Task): Long
    @Update
    fun updateLevel_Task(level_task: Level_Task)
    @Query("DELETE FROM Level_Task WHERE levelId = :levelId AND taskId = :taskId")
    fun deleteLevel_Task(levelId: Int, taskId: Int)
    @Delete
    fun deleteFromLevel_Task(Level_Task: Level_Task)
}