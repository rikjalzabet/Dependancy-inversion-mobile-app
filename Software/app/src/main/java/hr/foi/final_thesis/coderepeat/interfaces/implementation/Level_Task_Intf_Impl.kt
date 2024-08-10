package hr.foi.final_thesis.coderepeat.interfaces.implementation

import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Level_Task
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.interfaces.ILevel_Task

class Level_Task_Intf_Impl (private val context: Context) : ILevel_Task {
    private val db = AppDatabase.getDatabase(context)
    override fun getTasksForLevel(levelId: Int): List<Task> {
        return db.level_taskDao().getTasksForLevel(levelId)
    }

    override fun getLevelsForTask(taskId: Int): List<Level> {
        return db.level_taskDao().getLevelsForTask(taskId)
    }

    override fun getAllLevel_Tasks(): List<Level_Task> {
        return db.level_taskDao().getAllLevel_Tasks()
    }

    override fun insertLevel_Task(level_task: Level_Task) {
        db.level_taskDao().insertLevel_Task(level_task)
    }

    override fun updateLevel_Task(level_task: Level_Task) {
        db.level_taskDao().updateLevel_Task(level_task)
    }

    override fun deleteLevel_Task(levelId: Int, taskId: Int) {
        db.level_taskDao().deleteLevel_Task(levelId, taskId)
    }

    override fun deleteFromLevel_Task(Level_Task: Level_Task) {
        db.level_taskDao().deleteFromLevel_Task(Level_Task)
    }
}