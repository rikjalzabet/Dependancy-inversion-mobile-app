package hr.foi.final_thesis.coderepeat.repository

import hr.foi.final_thesis.coderepeat.entities.Task

interface TaskRepository {
    fun getAllTasks(): List<Task>
    fun getTaskById(taskId: Int): Task?
    fun insertTask(task: Task): Long
    fun updateTask(task: Task)
    fun deleteTask(task: Task)

}