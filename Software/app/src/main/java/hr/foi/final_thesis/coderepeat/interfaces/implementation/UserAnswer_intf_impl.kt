package hr.foi.final_thesis.coderepeat.interfaces.implementation

import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.entities.UserAnswer
import hr.foi.final_thesis.coderepeat.interfaces.IUserAnswer

class UserAnswer_intf_impl(private val context: Context): IUserAnswer{
    private val db = AppDatabase.getDatabase(context)
    override fun getAllUserAnswer(): List<UserAnswer> {
        return db.userAnswerDao().getAllUserAnswer()
    }
    override fun getUserAnswerById(id: Int): UserAnswer? {
        return db.userAnswerDao().getUserAnswerById(id)
    }
    override fun insertUserAnswers(userAnswer: UserAnswer): Long {
        return db.userAnswerDao().insertUserAnswers(userAnswer)
    }
    override fun updateUserAnswers(userAnswer: UserAnswer) {
        db.userAnswerDao().updateUserAnswers(userAnswer)
    }
    override fun deleteUserAnswers(userAnswer: UserAnswer) {
        db.userAnswerDao().deleteUserAnswers(userAnswer)
    }
}