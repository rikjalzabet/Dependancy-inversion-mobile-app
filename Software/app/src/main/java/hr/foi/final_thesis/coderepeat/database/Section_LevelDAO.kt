package hr.foi.final_thesis.coderepeat.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Section
import hr.foi.final_thesis.coderepeat.entities.Section_Level
import hr.foi.final_thesis.coderepeat.entities.Task

@Dao
interface Section_LevelDAO {
    @Query("SELECT * FROM Section WHERE id IN (SELECT sectionId FROM Section_Level WHERE levelId = :levelId)")
    fun getSectionsForLevel(levelId: Int): List<Section>
    @Query("SELECT * FROM Level WHERE id IN (SELECT levelId FROM Section_Level WHERE sectionId = :sectionId)")
    fun getLevelsForSection(sectionId: Int): List<Level>
    @Query("SELECT * FROM Section_Level")
    fun getAllSection_Levels(): List<Section_Level>
    @Insert
    fun insertSection_Level(section_level: Section_Level): Long
    @Update
    fun updateSection_Level(section_level: Section_Level)
    @Delete
    fun deleteFromSection_Level(section_level: Section_Level)
    @Query("DELETE FROM Section_Level WHERE levelId = :levelId AND sectionId = :sectionId")
    fun deleteSection_Level(levelId: Int, sectionId: Int)
}