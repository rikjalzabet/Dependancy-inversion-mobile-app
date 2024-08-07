package hr.foi.final_thesis.coderepeat.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hr.foi.final_thesis.coderepeat.entities.Section

@Dao
interface SectionDAO {
    @Insert
    fun insert(section: Section)
    @Query("SELECT * FROM section")
    fun getAllSections(): List<Section>
}