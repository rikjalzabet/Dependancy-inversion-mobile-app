package hr.foi.final_thesis.coderepeat.interfaces.tasks

import android.content.Context
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.adapters.tasks.MatchAnswerOptionsAdapter
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.entities.Task_UserAnswer
import hr.foi.final_thesis.coderepeat.entities.UserAnswer
import hr.foi.final_thesis.coderepeat.helpers.TaskType

class MatchTheAnswersTask(
    private val givenContext: Context
): ITaskHandler{
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

    override fun getTaskOptions(taskId: Int): List<String> {
        //val options = TaskImpl.getTaskById(taskId)?.options ?: ""
        //return options.split("|##|")
        val task=getTask(taskId)
        return task?.options?.split("|##|") ?: emptyList()
    }

    override fun getTaskCorrectAnswer(taskId: Int): String {
        return TaskImpl.getTaskById(taskId)?.correctAnswer ?: ""
    }

    override fun getTaskGrade(taskId: Int): Boolean {
        return TaskImpl.getTaskById(taskId)?.isAnswerCorrect ?: false
    }

    override fun getTaskType(taskId: Int): String {
        return "MATCH_THE_ANSWERS"
    }

    override fun getTaskQuestionMultiple(taskId: Int): List<String> {
        //val question = TaskImpl.getTaskById(taskId)?.question ?: ""
        //return question.split("|##|")
        val task=getTask(taskId)
        return task?.question?.split("|##|") ?: emptyList()
    }

    override fun setUserAnswer(userAnswer: String, taskId: Int, userAnswerId: Int) {
        UserAnswerImpl.insertUserAnswers(UserAnswer(userAnswer = userAnswer, userMultipleAnswer = ""))
        val newUserAnswerId = UserAnswerImpl.getLatestInsertedUserAnswer().id
        TaskUserAnswerImpl.insertTask_UserAnswer(Task_UserAnswer(taskId = taskId, answerId = newUserAnswerId.toInt()))
    }

    override fun getUserAnswer(id: Int): String {
        return UserAnswerImpl.getUserAnswerById(id)?.userAnswer ?: ""
    }

    override fun getUserLastAnswer(): UserAnswer {
        return UserAnswerImpl.getLatestInsertedUserAnswer()
    }

    override fun validateUserAnswerWithCorrectAnswer(userAnswerId: Int, taskId: Int): Boolean {
        val userAnswer = UserAnswerImpl.getUserAnswerById(userAnswerId)?.userAnswer ?: ""
        val correctAnswer = TaskImpl.getTaskById(taskId)?.correctAnswer ?: ""
        Log.i("TaskGameInfoIntf", "MATCH_THE_ANSWERS - User Answer: $userAnswer")
        Log.i("TaskGameInfoIntf", "MATCH_THE_ANSWERS - Correct Answer: $correctAnswer")
        return userAnswer.split("|##|").sorted() == correctAnswer.split("|##|").sorted()
    }

    override fun getTaskQuestion(taskId: Int): String {
        return ""
    }
}
