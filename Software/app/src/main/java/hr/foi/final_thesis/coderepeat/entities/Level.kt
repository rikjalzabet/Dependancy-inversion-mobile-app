package hr.foi.final_thesis.coderepeat.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Level")
data class Level(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val sectionId: Int,
    val name: String,
    val description: String
)
