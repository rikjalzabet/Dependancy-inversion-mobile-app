package hr.foi.final_thesis.coderepeat.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "Section_Level",
    primaryKeys = ["sectionId", "levelId"],
    foreignKeys = [
        ForeignKey(
            entity = Section::class,
            parentColumns = ["id"],
            childColumns = ["sectionId"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Level::class,
            parentColumns = ["id"],
            childColumns = ["levelId"],
            onDelete = CASCADE
        )
    ]
)
data class Section_Level(
    val sectionId: Int,
    val levelId: Int
)