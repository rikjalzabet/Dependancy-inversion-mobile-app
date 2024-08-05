package hr.foi.final_thesis.coderepeat.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Streak")
data class Streak (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val currentStreak: Int,
    val lastActiveDate: String
)