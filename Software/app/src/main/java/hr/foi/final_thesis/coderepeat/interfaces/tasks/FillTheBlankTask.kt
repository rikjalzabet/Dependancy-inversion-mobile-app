package hr.foi.final_thesis.coderepeat.interfaces.tasks


import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.entities.Task_UserAnswer
import hr.foi.final_thesis.coderepeat.entities.UserAnswer
import hr.foi.final_thesis.coderepeat.interfaces.ILevel
import hr.foi.final_thesis.coderepeat.interfaces.ILevel_Task
import hr.foi.final_thesis.coderepeat.interfaces.ITask
import hr.foi.final_thesis.coderepeat.interfaces.ITask_UserAnswer
import hr.foi.final_thesis.coderepeat.interfaces.IUserAnswer

class FillTheBlankTask(
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

    override fun getTaskByType(levelId: Int, taskType: String): Task? {
        return LevelTaskImpl.getTaskByLevelIdAndTaskType(levelId, taskType)
    }

    override fun getTaskQuestion(taskId: Int): String {
        return TaskImpl.getTaskById(taskId)?.question ?: ""
    }

    override fun getTaskOptions(taskId: Int): List<String> {
        return emptyList()
    }

    override fun getTaskCorrectAnswer(taskId: Int): String {
        return TaskImpl.getTaskById(taskId)?.correctAnswer ?: ""
    }

    override fun getTaskGrade(taskId: Int): Boolean {
        return TaskImpl.getTaskById(taskId)?.isAnswerCorrect ?: false
    }

    override fun getTaskType(taskId: Int): String {
        return "FILL_IN_THE_BLANK"
    }

    override fun getTaskQuestionMultiple(taskId: Int): List<String> {
        return emptyList()
    }

    override fun setUserAnswer(userAnswer: String, taskId: Int, userAnswerId: Int) {
        UserAnswerImpl.insertUserAnswers(UserAnswer(userAnswer = userAnswer.lowercase(), userMultipleAnswer = ""))
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
        val userAnswer = UserAnswerImpl.getUserAnswerById(userAnswerId)?.userAnswer?.trim()?.lowercase() ?: ""
        val correctAnswer = TaskImpl.getTaskById(taskId)?.correctAnswer?.trim()?.lowercase() ?: ""
        return userAnswer.equals(correctAnswer, ignoreCase = true)
    }
}