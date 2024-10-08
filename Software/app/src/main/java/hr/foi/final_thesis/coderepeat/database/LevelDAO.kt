package hr.foi.final_thesis.coderepeat.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import hr.foi.final_thesis.coderepeat.entities.Level

@Dao
interface LevelDAO {
    @Query("SELECT * FROM Level")
    fun getAllLevels():List<Level>
    @Query("SELECT * FROM Level WHERE id = :id")
    fun getLevelById(id: Int): Level?
    @Query("SELECT score FROM Level WHERE id = :levelId")
    fun getLevelPointsByLevelId(levelId: Int): Int
    @Insert
    fun insertLevel(level: Level): Long
    @Update
    fun updateLevel(level: Level)
    @Delete
    fun deleteLevel(level: Level)
}