import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Level_Task
import hr.foi.final_thesis.coderepeat.entities.Section
import hr.foi.final_thesis.coderepeat.entities.Section_Level
import hr.foi.final_thesis.coderepeat.entities.Streak
import hr.foi.final_thesis.coderepeat.entities.Task

fun populateData(context: Context) {
    populateSection(context)
    populateLevel(context)
    populateTask(context)
    populateSection_Level(context)
    populateLevel_Task(context)
    populateStreak(context)
}

fun populateSection(context: Context) {
    val db = AppDatabase.getDatabase(context)
    val sections = listOf(
        Section(title = "Section 1"),
        Section(title = "Section 2"),
        Section(title = "New Section") // New section
    )
    sections.forEach { db.sectionDao().insertSection(it) }
}

fun populateLevel(context: Context) {
    val db = AppDatabase.getDatabase(context)
    val sections = db.sectionDao().getAllSections()
    val levels = listOf(
        Level(name = "Level 1", description = "Basic tasks"),
        Level(name = "Level 2", description = "Intermediate tasks"),
        Level(name = "New Level", description = "New tasks") // New level
    )
    levels.forEach { db.levelDao().insertLevel(it) }
}

fun populateTask(context: Context) {
    val db = AppDatabase.getDatabase(context)
    val tasks = listOf(
        Task(type = "single_choice", question = "What is 2 + 2?", correctAnswer = "4", options = "2,3,4,5"),
        Task(type = "multiple_choice", question = "Select all numbers types.", correctAnswer = "Int, Double", options = "String, Int, Double, Char"),
        Task(type = "fill_blank", question = "Int is what type?", correctAnswer = "number"),
        Task(type = "code", question = "what operator is used for or in the code: if(a==null __ b==null){ print('a or b are null')}", correctAnswer = "||"),
        Task(type = "new_type", question = "New question", correctAnswer = "New answer") // New task
    )
    tasks.forEach { db.taskDao().insertTask(it) }
}

fun populateSection_Level(context: Context) {
    val db = AppDatabase.getDatabase(context)
    val sections = db.sectionDao().getAllSections()
    val levels = db.levelDao().getAllLevels()
    val section_levels = listOf(
        Section_Level(sectionId = sections[0].id, levelId = levels[0].id),
        Section_Level(sectionId = sections[1].id, levelId = levels[1].id),
        Section_Level(sectionId = sections[2].id, levelId = levels[2].id) // New section_level
    )
    section_levels.forEach { db.section_levelDao().insertSection_Level(it) }
}

fun populateLevel_Task(context: Context) {
    val db = AppDatabase.getDatabase(context)
    val levels = db.levelDao().getAllLevels()
    val tasks = db.taskDao().getAllTasks()
    val level_tasks = listOf(
        Level_Task(levelId = levels[0].id, taskId = tasks[0].id),
        Level_Task(levelId = levels[0].id, taskId = tasks[1].id),
        Level_Task(levelId = levels[0].id, taskId = tasks[2].id),
        Level_Task(levelId = levels[1].id, taskId = tasks[3].id),
        Level_Task(levelId = levels[2].id, taskId = tasks[4].id) // New level_task
    )
    level_tasks.forEach { db.level_taskDao().insertLevel_Task(it) }
}

fun populateStreak(context: Context) {
    val db = AppDatabase.getDatabase(context)
    val streak = Streak(currentStreak = 0, lastActiveDate = "")
    db.streakDao().insertStreak(streak)
}