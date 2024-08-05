package hr.foi.final_thesis.coderepeat.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.foi.final_thesis.coderepeat.entities.Task

@Dao
interface TaskDAO {
    @Query("SELECT * FROM Task")
    fun getAllTasks(): List<Task>
    @Query("SELECT * FROM Task WHERE levelId = :levelId")
    fun getTasksForLevel(levelId: Int): List<Task>
    @Query("SELECT * FROM Task WHERE id = :id")
    fun getTaskById(id: Int): Task?
    @Insert
    fun insertTask(task: Task): Long
    @Update
    fun updateTask(task: Task)
    @Delete
    fun deleteTask(task:Task)
}