package hr.foi.final_thesis.coderepeat.interfaces.implementation

import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.database.Task_UserAnswerDAO
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.entities.Task_UserAnswer
import hr.foi.final_thesis.coderepeat.entities.UserAnswer
import hr.foi.final_thesis.coderepeat.interfaces.ITask_UserAnswer

class Task_UserAnswer_intf_impl(private val task_userAnswerDao: Task_UserAnswerDAO):ITask_UserAnswer {
    override fun getTaskForAnswer(answerId: Int): List<Task> {
        return task_userAnswerDao.getTaskForAnswer(answerId)
    }
    override fun getAnswerForTask(taskId: Int): List<UserAnswer> {
        return task_userAnswerDao.getAnswerForTask(taskId)
    }
    override fun getAllTask_UserAnswers(): List<Task_UserAnswer> {
        return task_userAnswerDao.getAllTask_UserAnswers()
    }

    override fun getUserAnswerForTask(taskId: Int): List<UserAnswer> {
        return task_userAnswerDao.getUserAnswerForTask(taskId)
    }

    override fun insertTask_UserAnswer(task_userAnswer: Task_UserAnswer): Long {
        return task_userAnswerDao.insertTask_UserAnswer(task_userAnswer)
    }
    override fun updateTask_UserAnswer(task_userAnswer: Task_UserAnswer) {
        task_userAnswerDao.updateTask_UserAnswer(task_userAnswer)
    }
    override fun deleteTask_UserAnswer(task_userAnswer: Task_UserAnswer) {
        task_userAnswerDao.deleteTask_UserAnswer(task_userAnswer)
    }
    override fun deleteTask_UserAnswer(taskId: Int, answerId: Int) {
        task_userAnswerDao.deleteTask_UserAnswer(taskId, answerId)
    }
}