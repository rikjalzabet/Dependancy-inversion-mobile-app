package hr.foi.final_thesis.coderepeat.interfaces

import hr.foi.final_thesis.coderepeat.entities.Task

interface ITask {
    fun getAllTasks(): List<Task>
    fun getTaskById(taskId: Int): Task?
    fun insertTask(task: Task): Long
    fun updateTask(task: Task)
    fun deleteTask(task: Task)

}