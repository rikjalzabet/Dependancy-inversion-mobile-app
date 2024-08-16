package hr.foi.final_thesis.coderepeat.interfaces

import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.entities.Task_UserAnswer
import hr.foi.final_thesis.coderepeat.entities.UserAnswer

interface IUserAnswer {
    fun getAllUserAnswer():List<UserAnswer>
    fun getUserAnswerById(id: Int): UserAnswer?
    fun insertUserAnswers(userAnswer: UserAnswer): Long
    fun updateUserAnswers(userAnswer: UserAnswer)
    fun deleteUserAnswers(userAnswer: UserAnswer)
}