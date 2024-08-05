package hr.foi.final_thesis.coderepeat.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Task")
data class Task (
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val levelId: Int,
    val type: String,
    val question: String,
    val correctAnswer: String,
    val options: String? = null
)