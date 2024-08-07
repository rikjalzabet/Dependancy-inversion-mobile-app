package hr.foi.final_thesis.coderepeat.repository

import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Level_Task
import hr.foi.final_thesis.coderepeat.entities.Task

interface Level_TaskRepository {
    fun getTasksForLevel(levelId: Int): List<Task>
    fun getLevelsForTask(taskId: Int): List<Level>
    fun getAllLevel_Tasks(): List<Level_Task>
    fun insertLevel_Task(level_task: Level_Task)
    fun updateLevel_Task(level_task: Level_Task)
    fun deleteLevel_Task(levelId: Int, taskId: Int)
    fun deleteFromLevel_Task(levelId: Int, taskId: Int)
}