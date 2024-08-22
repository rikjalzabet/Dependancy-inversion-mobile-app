package hr.foi.final_thesis.coderepeat.interfaces.implementation

import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.entities.Task_UserAnswer
import hr.foi.final_thesis.coderepeat.entities.UserAnswer
import hr.foi.final_thesis.coderepeat.interfaces.ITask_UserAnswer

class Task_UserAnswer_intf_impl(private val context: Context):ITask_UserAnswer {
    private val db = AppDatabase.getDatabase(context)
    override fun getTaskForAnswer(answerId: Int): List<Task> {
        return db.task_userAnswerDao().getTaskForAnswer(answerId)
    }
    override fun getAnswerForTask(taskId: Int): List<UserAnswer> {
        return db.task_userAnswerDao().getAnswerForTask(taskId)
    }
    override fun getAllTask_UserAnswers(): List<Task_UserAnswer> {
        return db.task_userAnswerDao().getAllTask_UserAnswers()
    }

    override fun getUserAnswerForTask(taskId: Int): List<UserAnswer> {
        return db.task_userAnswerDao().getUserAnswerForTask(taskId)
    }

    override fun insertTask_UserAnswer(task_userAnswer: Task_UserAnswer): Long {
        return db.task_userAnswerDao().insertTask_UserAnswer(task_userAnswer)
    }
    override fun updateTask_UserAnswer(task_userAnswer: Task_UserAnswer) {
        db.task_userAnswerDao().updateTask_UserAnswer(task_userAnswer)
    }
    override fun deleteTask_UserAnswer(task_userAnswer: Task_UserAnswer) {
        db.task_userAnswerDao().deleteTask_UserAnswer(task_userAnswer)
    }
    override fun deleteTask_UserAnswer(taskId: Int, answerId: Int) {
        db.task_userAnswerDao().deleteTask_UserAnswer(taskId, answerId)
    }
}