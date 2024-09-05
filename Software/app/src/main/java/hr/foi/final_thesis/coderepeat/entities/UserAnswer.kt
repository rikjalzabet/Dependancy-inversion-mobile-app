package hr.foi.final_thesis.coderepeat.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserAnswers")
data class UserAnswer(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userAnswer: String,
    val userMultipleAnswer: String,
)