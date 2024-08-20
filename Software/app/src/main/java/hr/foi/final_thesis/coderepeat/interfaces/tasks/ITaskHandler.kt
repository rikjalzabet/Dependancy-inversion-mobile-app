package hr.foi.final_thesis.coderepeat.interfaces.tasks

import android.view.View
import hr.foi.final_thesis.coderepeat.helpers.TaskType

interface ITaskHandler {
    fun getQuestion():String
    fun getOptions():List<String>
    fun getDefinitions():List<String>
    fun getOptionsForDefinitions():List<String>
    fun getAnswer():String
    fun setAnswer(answer:String)
    fun validateAnswer():Boolean
    fun getCorrectAnswer():String
    fun displayTask(view: View)
    fun getUserAnswer():String
    fun getTaskType():TaskType

}