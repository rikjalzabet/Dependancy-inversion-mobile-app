package hr.foi.final_thesis.coderepeat.interfaces.tasks

import android.content.Context
import android.view.View
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.entities.UserAnswer
import hr.foi.final_thesis.coderepeat.helpers.TaskType
import hr.foi.final_thesis.coderepeat.interfaces.ILevel
import hr.foi.final_thesis.coderepeat.interfaces.ILevel_Task
import hr.foi.final_thesis.coderepeat.interfaces.ISection
import hr.foi.final_thesis.coderepeat.interfaces.ISection_Level
import hr.foi.final_thesis.coderepeat.interfaces.IStreak
import hr.foi.final_thesis.coderepeat.interfaces.ITask
import hr.foi.final_thesis.coderepeat.interfaces.ITask_UserAnswer
import hr.foi.final_thesis.coderepeat.interfaces.IUserAnswer
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Level_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Level_Task_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Section_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Section_Level_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Streak_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Task_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Task_UserAnswer_intf_impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.UserAnswer_intf_impl

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
}