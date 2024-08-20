package hr.foi.final_thesis.coderepeat.interfaces.tasks

import android.view.View
import hr.foi.final_thesis.coderepeat.helpers.TaskType

class MultipleChoiceSingleChoiceTask(
    private val question: String,
    private val options: List<String>,
    private var selectedOption: String,
    private val correctAnswer: String
) : ITaskHandler {

    override fun getQuestion(): String = question

    override fun getOptions(): List<String> = options

    override fun getDefinitions(): List<String> = emptyList()

    override fun getOptionsForDefinitions(): List<String> = emptyList()

    override fun getAnswer(): String = selectedOption

    override fun setAnswer(answer: String) {
        this.selectedOption = answer
    }

    override fun validateAnswer(): Boolean = selectedOption == correctAnswer

    override fun getCorrectAnswer(): String = correctAnswer

    override fun displayTask(view: View) {
        // Implement logic to display the single choice task in the view
        // For example, populate a RadioGroup with options
    }

    override fun getUserAnswer(): String = selectedOption

    override fun getTaskType(): TaskType = TaskType.SINGLE_CHOICE
}
