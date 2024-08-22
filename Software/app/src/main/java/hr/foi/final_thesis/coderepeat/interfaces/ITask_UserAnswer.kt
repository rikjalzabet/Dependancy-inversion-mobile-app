package hr.foi.final_thesis.coderepeat.interfaces

import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.entities.Task_UserAnswer
import hr.foi.final_thesis.coderepeat.entities.UserAnswer

interface ITask_UserAnswer {
    fun getTaskForAnswer(answerId: Int): List<Task>
    fun getAnswerForTask(taskId: Int): List<UserAnswer>
    fun getAllTask_UserAnswers(): List<Task_UserAnswer>
    fun getUserAnswerForTask(taskId: Int): List<UserAnswer>
    fun insertTask_UserAnswer(task_userAnswer: Task_UserAnswer): Long
    fun updateTask_UserAnswer(task_userAnswer: Task_UserAnswer)
    fun deleteTask_UserAnswer(task_userAnswer: Task_UserAnswer)
    fun deleteTask_UserAnswer(taskId: Int, answerId: Int)
}