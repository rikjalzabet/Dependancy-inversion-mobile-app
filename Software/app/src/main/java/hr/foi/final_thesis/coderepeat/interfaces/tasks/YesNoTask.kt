package hr.foi.final_thesis.coderepeat.interfaces.tasks

import android.content.Context
import android.util.Log
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.entities.Task_UserAnswer
import hr.foi.final_thesis.coderepeat.entities.UserAnswer
import hr.foi.final_thesis.coderepeat.interfaces.ILevel
import hr.foi.final_thesis.coderepeat.interfaces.ILevel_Task
import hr.foi.final_thesis.coderepeat.interfaces.ITask
import hr.foi.final_thesis.coderepeat.interfaces.ITask_UserAnswer
import hr.foi.final_thesis.coderepeat.interfaces.IUserAnswer

class YesNoTask(
    private val LevelImpl: ILevel,
    private val TaskImpl: ITask,
    private val LevelTaskImpl: ILevel_Task,
    private val TaskUserAnswerImpl: ITask_UserAnswer,
    private val UserAnswerImpl: IUserAnswer
) : ITaskHandler {
    override fun getLevel(levelId: Int): Level? {
        return LevelImpl.getLevelById(levelId)
    }

    override fun getTask(taskId: Int): Task? {
        return TaskImpl.getTaskById(taskId)
    }

    override fun getTaskByType(levelId: Int, taskType: String): Task {
        return LevelTaskImpl.getTaskByLevelIdAndTaskType(levelId, taskType)
    }

    override fun getTaskQuestion(taskId: Int): String {
        return TaskImpl.getTaskById(taskId)?.question ?: ""
    }

    override fun getTaskOptions(taskId: Int): List<String> {
        return listOf("True", "False")
    }

    override fun getTaskCorrectAnswer(taskId: Int): String {
        return TaskImpl.getTaskById(taskId)?.correctAnswer.toString()
    }

    override fun getTaskGrade(taskId: Int): Boolean {
        return TaskImpl.getTaskById(taskId)?.isAnswerCorrect ?: false
    }

    override fun getTaskType(taskId: Int): String {
        return "YES_NO"
    }

    override fun getTaskQuestionMultiple(taskId: Int): List<String> {
        return emptyList()
    }

    override fun setUserAnswer(userAnswer: String, taskId: Int, userAnswerId: Int) {
        UserAnswerImpl.insertUserAnswers(UserAnswer(userAnswer=userAnswer, userMultipleAnswer = ""))
        val getNewAddedUserAnswer=UserAnswerImpl.getLatestInsertedUserAnswer().id
        val connectTaskUserAnswer = TaskUserAnswerImpl.insertTask_UserAnswer(Task_UserAnswer(taskId=taskId, answerId=(getNewAddedUserAnswer.toInt())))
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
        Log.i("YesNoTask","User answer: ${userAnswer?.userAnswer} Task answer: ${task?.correctAnswer}")
        return userAnswer?.userAnswer?.toString() == task?.correctAnswer.toString()
    }
}
