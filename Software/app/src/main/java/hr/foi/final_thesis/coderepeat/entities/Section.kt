package hr.foi.final_thesis.coderepeat.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Section")
data class Section (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
)