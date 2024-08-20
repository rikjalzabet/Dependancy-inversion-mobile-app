package hr.foi.final_thesis.coderepeat.interfaces.tasks

import android.view.View
import hr.foi.final_thesis.coderepeat.helpers.TaskType

class YesNoTask(
    private val question: String,
    private var answer: Boolean,
    private val correctAnswer: Boolean
) : ITaskHandler {

    override fun getQuestion(): String = question

    override fun getOptions(): List<String> = listOf("True", "False")

    override fun getDefinitions(): List<String> = emptyList()

    override fun getOptionsForDefinitions(): List<String> = emptyList()

    override fun getAnswer(): String = if (answer) "True" else "False"

    override fun setAnswer(answer: String) {
        this.answer = answer.equals("True", ignoreCase = true)
    }

    override fun validateAnswer(): Boolean = answer == correctAnswer

    override fun getCorrectAnswer(): String = if (correctAnswer) "True" else "False"

    override fun displayTask(view: View) {
        // Implement logic to display the Yes/No task in the view
        // For example, set question text and provide Yes/No options
    }

    override fun getUserAnswer(): String = if (answer) "True" else "False"

    override fun getTaskType(): TaskType = TaskType.YES_NO
}
