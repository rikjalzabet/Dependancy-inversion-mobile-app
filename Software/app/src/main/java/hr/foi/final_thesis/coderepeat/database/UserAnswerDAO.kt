package hr.foi.final_thesis.coderepeat.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.foi.final_thesis.coderepeat.entities.UserAnswer

@Dao
interface UserAnswerDAO {
    @Query("SELECT * FROM UserAnswers")
    fun getAllUserAnswer():List<UserAnswer>
    @Query("SELECT * FROM UserAnswers WHERE id = :id")
    fun getUserAnswerById(id: Int): UserAnswer?
    @Query("DELETE FROM UserAnswers")
    fun deleteAllUserAnswers()
    @Insert
    fun insertUserAnswers(userAnswer: UserAnswer): Long
    @Query("SELECT * FROM UserAnswers ORDER BY id DESC LIMIT 1")
    fun getLatestInsertedUserAnswer(): UserAnswer
    @Update
    fun updateUserAnswers(userAnswer: UserAnswer)
    @Delete
    fun deleteUserAnswers(userAnswer: UserAnswer)
}