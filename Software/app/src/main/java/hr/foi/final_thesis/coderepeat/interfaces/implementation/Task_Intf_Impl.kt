package hr.foi.final_thesis.coderepeat.interfaces.implementation

import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.database.TaskDAO
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.interfaces.ITask

class Task_Intf_Impl(private val taskDao: TaskDAO): ITask {
    override fun getAllTasks(): List<Task> {
        return taskDao.getAllTasks()
    }

    override fun getTaskById(taskId: Int): Task? {
        return taskDao.getTaskById(taskId)
    }

    override fun getTasksByType(type: String): List<Task> {
        return taskDao.getTasksByType(type)
    }

    override fun insertTask(task: Task): Long {
        return taskDao.insertTask(task)
    }

    override fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    override fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }
}