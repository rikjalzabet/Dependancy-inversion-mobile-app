package hr.foi.final_thesis.coderepeat.repository.implementation

import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.repository.TaskRepository

class TaskRepositoryImpl(private val context: Context): TaskRepository {
    private val db = AppDatabase.getDatabase(context)
    override fun getAllTasks(): List<Task> {
        return db.taskDao().getAllTasks()
    }

    override fun getTasksForLevel(levelId: Int): List<Task> {
        return db.taskDao().getTasksForLevel(levelId)
    }
    override fun getTaskById(taskId: Int): Task? {
        return db.taskDao().getTaskById(taskId)
    }
    override fun insertTask(task: Task): Long {
        return db.taskDao().insertTask(task)
    }

    override fun updateTask(task: Task) {
        db.taskDao().updateTask(task)
    }

    override fun deleteTask(task: Task) {
        db.taskDao().deleteTask(task)
    }
}