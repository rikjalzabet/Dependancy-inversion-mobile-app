package hr.foi.final_thesis.coderepeat.interfaces.tasks

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.entities.Task_UserAnswer
import hr.foi.final_thesis.coderepeat.entities.UserAnswer
import hr.foi.final_thesis.coderepeat.helpers.TaskType

class MultipleChoiceTask(
    private val givenContext: Context
) : ITaskHandler {
    override var context: Context = givenContext

    override fun getLevel(levelId: Int): Level? {
        return LevelImpl.getLevelById(levelId)
    }

    override fun getTask(taskId: Int): Task? {
        return TaskImpl.getTaskById(taskId)
    }

    override fun getTaskByType(levelId: Int, taskType: String): Task? {
        return LevelTaskImpl.getTaskByLevelIdAndTaskType(levelId, taskType)
    }

    override fun getTaskQuestion(taskId: Int): String {
        return TaskImpl.getTaskById(taskId)?.question ?: ""
    }

    override fun getTaskOptions(taskId: Int): List<String> {
        val options = TaskImpl.getTaskById(taskId)?.options ?: ""
        return options.split("|##|").map { it.trim() }
    }

    override fun getTaskCorrectAnswer(taskId: Int): String {
        return TaskImpl.getTaskById(taskId)?.correctAnswer ?: ""
    }

    override fun getTaskGrade(taskId: Int): Boolean {
        return TaskImpl.getTaskById(taskId)?.isAnswerCorrect ?: false
    }

    override fun getTaskType(taskId: Int): String {
        return "MULTIPLE_CHOICE_MULTIPLE_ANSWERS"
    }

    override fun getTaskQuestionMultiple(): List<String> {
        return emptyList()
    }

    override fun setUserAnswer(userAnswer: String, taskId: Int, userAnswerId: Int) {
        UserAnswerImpl.insertUserAnswers(UserAnswer(userAnswer = "", userMultipleAnswer = userAnswer))
        val newUserAnswerId = UserAnswerImpl.getLatestInsertedUserAnswer().id
        TaskUserAnswerImpl.insertTask_UserAnswer(Task_UserAnswer(taskId = taskId, answerId = newUserAnswerId.toInt()))
    }

    override fun getUserAnswer(id: Int): String {
        return UserAnswerImpl.getUserAnswerById(id)?.userMultipleAnswer ?: ""
    }

    override fun getUserLastAnswer(): UserAnswer {
        return UserAnswerImpl.getLatestInsertedUserAnswer()
    }

    override fun validateUserAnswerWithCorrectAnswer(userAnswerId: Int, taskId: Int): Boolean {
        val userAnswer = UserAnswerImpl.getUserAnswerById(userAnswerId)?.userMultipleAnswer?.split("|##|") ?: emptyList()
        val correctAnswers = TaskImpl.getTaskById(taskId)?.correctAnswer?.split("|##|") ?: emptyList()
        return userAnswer.sorted() == correctAnswers.sorted()
    }
}