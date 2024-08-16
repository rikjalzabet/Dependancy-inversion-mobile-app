package hr.foi.final_thesis.coderepeat.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.foi.final_thesis.coderepeat.entities.Streak

@Dao
interface StreakDAO {
    @Query("SELECT currentStreak FROM Streak WHERE id = :id")
    fun getCurrentStreak(id: Int): Streak?
    @Query("SELECT startDate FROM Streak WHERE id = :id")
    fun getStartStreakById(id: Int): Streak?
    @Query("SELECT lastActiveDate FROM Streak WHERE id = :id")
    fun getLastActiveStreakById(id: Int): Streak?
    @Query("SELECT * FROM Streak WHERE id = :id")
    fun getStreakById(id: Int): Streak?
    @Query("SELECT * FROM Streak")
    fun getAllStreaks(): List<Streak>
    @Insert
    fun insertStreak(streak: Streak)
    @Update
    fun updateStreak(streak: Streak)
    @Delete
    fun deleteStreak(streak: Streak)
}