package hr.foi.final_thesis.coderepeat.interfaces.tasks

import android.view.View
import hr.foi.final_thesis.coderepeat.entities.UserAnswer
import hr.foi.final_thesis.coderepeat.helpers.TaskType

class FillTheBlankTask(
    private val question: String,
    private var answer: String,
    private val correctAnswer: String
) : ITaskHandler{
    override fun getQuestion(): String = question
    override fun getOptions(): List<String> = emptyList()
    override fun getDefinitions(): List<String> = emptyList()
    override fun getOptionsForDefinitions(): List<String> = emptyList()
    override fun getAnswer(): String = answer
    override fun setAnswer(answer: String) {
        this.answer = answer
    }
    override fun validateAnswer(): Boolean = answer == correctAnswer
    override fun getCorrectAnswer(): String = correctAnswer
    override fun displayTask(view: View) {
        // Implement logic to display the fill-in-the-blank task in the view
        // For example, set the question in a TextView
    }
    override fun getUserAnswer(): String = answer
    override fun getTaskType(): TaskType = TaskType.FILL_IN_THE_BLANK
}