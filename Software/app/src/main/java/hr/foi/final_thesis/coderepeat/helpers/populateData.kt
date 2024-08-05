package hr.foi.final_thesis.coderepeat.helpers

import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Streak
import hr.foi.final_thesis.coderepeat.entities.Task

fun populateData(context: Context) {
    val db = AppDatabase.getDatabase(context)

    val levels = listOf(
        Level(name = "Level 1", description = "Basic tasks"),
        Level(name = "Level 2", description = "Intermediate tasks")
    )
    levels.forEach { db.levelDao().insertLevel(it) }

    val tasks = listOf(
        Task(levelId = 1, type = "single_choice", question = "What is 2 + 2?", correctAnswer = "4", options = "2,3,4,5"),
        Task(levelId = 1, type = "multiple_choice", question = "Select all numbers types.", correctAnswer = "Int, Double", options = "String, Int, Double, Char"),
        Task(levelId = 1, type = "fill_blank", question = "Int is what type?", correctAnswer = "number"),
        Task(levelId = 2, type = "code", question = "what operator is used for or in the code: if(a==null __ b==null){ print('a or b are null')}", correctAnswer = "||")
    )
    tasks.forEach { db.taskDao().insertTask(it) }

    val streak = Streak(currentStreak = 0, lastActiveDate = "")
    db.streakDao().insertStreak(streak)
}
