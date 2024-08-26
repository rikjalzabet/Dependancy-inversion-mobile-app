package hr.foi.final_thesis.coderepeat.interfaces.implementation

import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.database.UserAnswerDAO
import hr.foi.final_thesis.coderepeat.entities.UserAnswer
import hr.foi.final_thesis.coderepeat.interfaces.IUserAnswer

class UserAnswer_intf_impl(private val userAnswerDao: UserAnswerDAO): IUserAnswer{
    override fun getAllUserAnswer(): List<UserAnswer> {
        return userAnswerDao.getAllUserAnswer()
    }
    override fun getUserAnswerById(id: Int): UserAnswer? {
        return userAnswerDao.getUserAnswerById(id)
    }

    override fun getLatestInsertedUserAnswer(): UserAnswer {
        return userAnswerDao.getLatestInsertedUserAnswer()
    }

    override fun insertUserAnswers(userAnswer: UserAnswer): Long {
        return userAnswerDao.insertUserAnswers(userAnswer)
    }
    override fun updateUserAnswers(userAnswer: UserAnswer) {
        userAnswerDao.updateUserAnswers(userAnswer)
    }
    override fun deleteUserAnswers(userAnswer: UserAnswer) {
        userAnswerDao.deleteUserAnswers(userAnswer)
    }
}