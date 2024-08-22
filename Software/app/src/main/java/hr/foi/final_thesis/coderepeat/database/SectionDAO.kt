package hr.foi.final_thesis.coderepeat.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import hr.foi.final_thesis.coderepeat.entities.Section

@Dao
interface SectionDAO {
    @Query("SELECT * FROM Section")
    fun getAllSections(): List<Section>
    @Query("SELECT * FROM Section WHERE id = :id")
    fun getSectionById(id: Int): Section?
    @Insert
    fun insertSection(section: Section): Long
    @Update
    fun updateSection(section: Section)
    @Delete
    fun deleteSection(section: Section)
    @Query("DELETE FROM Section WHERE id = :id")
    fun deleteSectionById(id: Int)
}