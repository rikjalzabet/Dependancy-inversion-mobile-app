package hr.foi.final_thesis.coderepeat.interfaces.tasks

import android.view.View
import hr.foi.final_thesis.coderepeat.helpers.TaskType

class MatchTheAnswersTask(
    private val question: String,
    private val answersOptions: List<String>,
    private val options: List<String>,
    private val matches: Map<String, String> // Definition to option mapping
) : ITaskHandler {

    override fun getQuestion(): String = question

    override fun getOptions(): List<String> = emptyList() // No options for match definitions

    override fun getDefinitions(): List<String> = answersOptions

    override fun getOptionsForDefinitions(): List<String> = options

    override fun getAnswer(): String = matches.entries.joinToString(", ") { "${it.key}: ${it.value}" }

    override fun setAnswer(answer: String) {
        // Implement logic to update the matches map with user answers
    }

    override fun validateAnswer(): Boolean = matches.all { (definition, option) -> option == getCorrectOptionForDefinition(definition) }

    override fun getCorrectAnswer(): String = matches.entries.joinToString(", ") { "${it.key}: ${it.value}" }

    override fun displayTask(view: View) {
        // Implement logic to display the match definitions task in the view
        // For example, use a RecyclerView to show definitions and spinners for options
    }

    override fun getUserAnswer(): String = matches.entries.joinToString(", ") { "${it.key}: ${it.value}" }

    private fun getCorrectOptionForDefinition(definition: String): String {
        // Implement logic to retrieve the correct option for a given definition
        return "" // Placeholder
    }

    override fun getTaskType(): TaskType = TaskType.MATCH_THE_ANSWER
}
