package hr.foi.final_thesis.coderepeat.interfaces.tasks

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Level_Task
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.entities.Task_UserAnswer
import hr.foi.final_thesis.coderepeat.entities.UserAnswer
import hr.foi.final_thesis.coderepeat.helpers.TaskType
import hr.foi.final_thesis.coderepeat.interfaces.ILevel
import hr.foi.final_thesis.coderepeat.interfaces.ILevel_Task
import hr.foi.final_thesis.coderepeat.interfaces.ITask
import hr.foi.final_thesis.coderepeat.interfaces.ITask_UserAnswer
import hr.foi.final_thesis.coderepeat.interfaces.IUserAnswer

class MultipleChoiceSingleChoiceTask(
    private val LevelImpl: ILevel,
    private val TaskImpl: ITask,
    private val LevelTaskImpl: ILevel_Task,
    private val TaskUserAnswerImpl: ITask_UserAnswer,
    private val UserAnswerImpl: IUserAnswer
): ITaskHandler{
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
        return TaskImpl.getTaskById(taskId)?.correctAnswer.toString()
    }

    override fun getTaskGrade(taskId: Int): Boolean {
        return TaskImpl.getTaskById(taskId)?.isAnswerCorrect ?: false
    }

    override fun getTaskType(taskId: Int): String {
        return "MULTIPLE_CHOICE_SINGLE_ANSWER"
    }

    override fun getTaskQuestionMultiple(taskId: Int): List<String> {
        return emptyList()
    }

    override fun setUserAnswer(userAnswer: String, taskId: Int, userAnswerId: Int) {
        UserAnswerImpl.insertUserAnswers(UserAnswer(userAnswer = userAnswer, userMultipleAnswer = ""))
        val newUserAnswerId = UserAnswerImpl.getLatestInsertedUserAnswer().id
        TaskUserAnswerImpl.insertTask_UserAnswer(Task_UserAnswer(taskId = taskId, answerId = newUserAnswerId.toInt()))
    }

    override fun getUserAnswer(id: Int): String {
        return UserAnswerImpl.getUserAnswerById(id)?.userAnswer.toString()
    }

    override fun getUserLastAnswer(): UserAnswer {
        return UserAnswerImpl.getLatestInsertedUserAnswer()
    }

    override fun validateUserAnswerWithCorrectAnswer(userAnswerId: Int, taskId: Int): Boolean {
        val userAnswer = UserAnswerImpl.getUserAnswerById(userAnswerId)
        val task = TaskImpl.getTaskById(taskId)
        Log.i("MultipleChoiceSingleCorrectTask", "User answer: ${userAnswer?.userAnswer} Task answer: ${task?.correctAnswer}")
        return userAnswer?.userAnswer == task?.correctAnswer
    }
    override fun deleteAllUserAnswers() {
        UserAnswerImpl.deleteAllUserAnswers()
    }

    override fun deleteAllTask_UserAnswers() {
        TaskUserAnswerImpl.deleteAllTask_UserAnswers()
    }
    override fun updateTaskPoints(taskId: Int, levelId: Int) {
        val task = TaskImpl.getTaskById(taskId)
        val level = LevelImpl.getLevelById(levelId)
        if (task != null && level != null) {
            LevelTaskImpl.updateLevel_Task(Level_Task(levelId = level.id, taskId = task.id, points = 1.0))
        }
    }
}
