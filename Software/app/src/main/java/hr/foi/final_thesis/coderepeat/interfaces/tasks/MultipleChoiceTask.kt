package hr.foi.final_thesis.coderepeat.interfaces.tasks

import android.view.View
import hr.foi.final_thesis.coderepeat.helpers.TaskType

class MultipleChoiceTask(
    private val question: String,
    private val options: List<String>,
    private val selectedOptions: MutableSet<String>,
    private val correctAnswers: Set<String>
) : ITaskHandler {

    override fun getQuestion(): String = question

    override fun getOptions(): List<String> = options

    override fun getDefinitions(): List<String> = emptyList()

    override fun getOptionsForDefinitions(): List<String> = emptyList()

    override fun getAnswer(): String = selectedOptions.joinToString(", ")

    override fun setAnswer(answer: String) {
        this.selectedOptions.add(answer)
    }

    override fun validateAnswer(): Boolean = selectedOptions == correctAnswers

    override fun getCorrectAnswer(): String = correctAnswers.joinToString(", ")

    override fun displayTask(view: View) {
        // Implement logic to display the multiple choice task in the view
        // For example, add CheckBoxes dynamically to a LinearLayout
    }

    override fun getUserAnswer(): String = selectedOptions.joinToString(", ")

    override fun getTaskType(): TaskType = TaskType.MULTIPLE_CHOICE
}
