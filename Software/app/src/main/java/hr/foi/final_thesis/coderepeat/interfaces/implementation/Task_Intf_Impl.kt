package hr.foi.final_thesis.coderepeat.interfaces.implementation

import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.interfaces.ITask

class Task_Intf_Impl(private val context: Context): ITask {
    private val db = AppDatabase.getDatabase(context)
    override fun getAllTasks(): List<Task> {
        return db.taskDao().getAllTasks()
    }

    override fun getTaskById(taskId: Int): Task? {
        return db.taskDao().getTaskById(taskId)
    }

    override fun getTasksByType(type: String): List<Task> {
        return db.taskDao().getTasksByType(type)
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