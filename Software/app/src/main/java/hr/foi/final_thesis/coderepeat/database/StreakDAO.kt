package hr.foi.final_thesis.coderepeat.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.foi.final_thesis.coderepeat.entities.Streak

@Dao
interface StreakDAO {
    @Query("SELECT * FROM Streak WHERE id = :id")
    fun getCurrentStreak(id: Int): Streak?
    @Query("SELECT * FROM Streak")
    fun getAllStreaks(): List<Streak>
    @Insert
    fun insertStreak(streak: Streak)
    @Update
    fun updateStreak(streak: Streak)
    @Delete
    fun deleteStreak(streak: Streak)
}