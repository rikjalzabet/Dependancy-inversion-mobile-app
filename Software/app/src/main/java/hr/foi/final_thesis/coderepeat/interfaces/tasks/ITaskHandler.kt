package hr.foi.final_thesis.coderepeat.interfaces.tasks

import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.entities.UserAnswer

interface ITaskHandler{
    fun getLevel(levelId: Int): Level?
    fun getTask(taskId: Int): Task?
    fun getTaskByType(levelId: Int, taskType: String): Task?
    fun getTaskQuestion(taskId: Int): String
    fun getTaskOptions(taskId: Int): List<String>
    fun getTaskCorrectAnswer(taskId: Int): String
    fun getTaskGrade(taskId: Int): Boolean
    fun getTaskType(taskId: Int): String
    fun getTaskQuestionMultiple(taskId: Int):List<String>
    fun setUserAnswer(userAnswer: String, taskId: Int, userAnswerId: Int)
    fun getUserAnswer(id: Int): String
    fun getUserLastAnswer(): UserAnswer
    fun validateUserAnswerWithCorrectAnswer(userAnswerId: Int, taskId: Int):Boolean
    fun deleteAllUserAnswers()
    fun deleteAllTask_UserAnswers()
    fun updateTaskPoints(taskId: Int, levelId: Int)
}