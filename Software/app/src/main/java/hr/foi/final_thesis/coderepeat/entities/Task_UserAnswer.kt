package hr.foi.final_thesis.coderepeat.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "Task_UserAnswer",
    primaryKeys = ["answerId", "taskId"],
    foreignKeys = [
        ForeignKey(
            entity = UserAnswer::class,
            parentColumns = ["id"],
            childColumns = ["answerId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["taskId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices=[Index("answerId"), Index("taskId")]
)
data class Task_UserAnswer(
    val answerId: Int,
    val taskId: Int
)
