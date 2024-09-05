package hr.foi.final_thesis.coderepeat.interfaces.implementation

import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.database.Level_TaskDAO
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Level_Task
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.interfaces.ILevel_Task

class Level_Task_Intf_Impl (private val level_taskDao: Level_TaskDAO) : ILevel_Task {
    override fun getTasksForLevel(levelId: Int): List<Task> {
        return level_taskDao.getTasksForLevel(levelId)
    }
    override fun getLevelsForTask(taskId: Int): List<Level> {
        return level_taskDao.getLevelsForTask(taskId)
    }
    override fun getAllLevel_Tasks(): List<Level_Task> {
        return level_taskDao.getAllLevel_Tasks()
    }
    override fun getPointsForLevelTask(levelId: Int, taskId: Int): Double {
        return level_taskDao.getPointsForLevelTask(levelId, taskId)
    }

    override fun getTaskByLevelIdAndTaskType(levelId: Int, type: String): Task {
        return level_taskDao.getTaskByLevelIdAndTaskType(levelId, type)
    }

    override fun insertLevel_Task(level_task: Level_Task) {
        level_taskDao.insertLevel_Task(level_task)
    }
    override fun updateLevel_Task(level_task: Level_Task) {
        level_taskDao.updateLevel_Task(level_task)
    }

    override fun updatePointsLevel_Task(levelId: Int, taskId: Int, points: Double) {
        level_taskDao.updatePointsLevel_Task(levelId, taskId, points)
    }

    override fun deleteLevel_Task(levelId: Int, taskId: Int) {
        level_taskDao.deleteLevel_Task(levelId, taskId)
    }
    override fun deleteFromLevel_Task(Level_Task: Level_Task) {
        level_taskDao.deleteFromLevel_Task(Level_Task)
    }
}