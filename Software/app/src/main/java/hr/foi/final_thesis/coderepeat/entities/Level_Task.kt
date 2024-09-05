package hr.foi.final_thesis.coderepeat.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Level_Task",
    primaryKeys = ["levelId", "taskId"],
    foreignKeys = [
        ForeignKey(
            entity = Level::class,
            parentColumns = ["id"],
            childColumns = ["levelId"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["taskId"],
            onDelete = CASCADE
        )
    ],
    indices=[Index("taskId"), Index("levelId")]
)
data class Level_Task(
    val levelId: Int,
    val taskId: Int,
    val points: Double
)