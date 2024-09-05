import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Level_Task
import hr.foi.final_thesis.coderepeat.entities.Section
import hr.foi.final_thesis.coderepeat.entities.Section_Level
import hr.foi.final_thesis.coderepeat.entities.Streak
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.helpers.DateConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

suspend fun populateData(context: Context) {
    populateSection(context)
    populateLevel(context)
    populateTask(context)
    populateSection_Level(context)
    populateLevel_Task(context)
    populateStreak(context)
}

suspend fun populateSection(context: Context) = withContext(Dispatchers.IO) {
    val db = AppDatabase.getDatabase(context)
    val sections = listOf(
        Section(title = "Beginner 1"),
        Section(title = "Beginner 2"),
        Section(title = "Intermediate 1"),
        Section(title = "Intermediate 2"),
        Section(title = "Advanced 1"),
        Section(title = "Advanced 2")
    )
    sections.forEach { db.sectionDao().insertSection(it) }
}

suspend fun populateLevel(context: Context) = withContext(Dispatchers.IO) {
    val db = AppDatabase.getDatabase(context)
    val sections = db.sectionDao().getAllSections()
    val levels = listOf(
        Level(name = "Basics 1", description = "Basic tasks"),
        Level(name = "Basic 2", description = "Intermediate tasks"),
        Level(name = "Nice 1", description = "New tasks"),
        Level(name = "Code", description = "Code fill in tasks"),
        Level(name="Match",description= "Match the answers tasks"),

        )
    levels.forEach { db.levelDao().insertLevel(it) }
}

suspend fun populateTask(context: Context) = withContext(Dispatchers.IO){
    val db = AppDatabase.getDatabase(context)
    val tasks = listOf(
        Task(type = "MULTIPLE_CHOICE_SINGLE_ANSWER", question = "What is 2 + 2?", correctAnswer = "4", options = "2 |##| 3 |##| 4 |##| 5"),
        Task(type = "MULTIPLE_CHOICE_SINGLE_ANSWER", question = "What is Double?", correctAnswer = "Decimal number", options = "Number |##| Decimal number |##| String |##| False"),
        Task(type = "MULTIPLE_CHOICE_MULTIPLE_ANSWERS", question = "Select all numbers types.", correctAnswer = "Int|##|Double", options = "String |##| Int |##| Double |##| Char"),
        Task(type = "MULTIPLE_CHOICE_MULTIPLE_ANSWERS", question = "Select all types that you can equal them to 0 and 1.", correctAnswer = "Int|##|Double|##|Bool", options = "Int |##| Double |##| Bool"),
        Task(type = "YES_NO", question = "We can do: Int a = false?", correctAnswer = "False", options = "True, False"),
        Task(type = "FILL_IN_THE_BLANK", question = "what operator is used for or in the code: if(a==null __ b==null){ print('a or b are null')}", correctAnswer = "||"),
        Task(type = "YES_NO", question = "Is INT number type?", correctAnswer = "True", options = "True, False"),
        Task(type = "FILL_IN_THE_BLANK", question = "what operator is used for and in the code: if(a==null __ b==null){ print('a and b are null')}", correctAnswer = "&&"),
        Task(type = "FILL_IN_THE_BLANK", question = "what operator is used for not equal to in the code: if(a__null){ print('a is not equal to null')}", correctAnswer = "!="),

        Task(type = "MATCH_THE_ANSWERS", question = "Int is a|##|Double is a|##|Bool is a", correctAnswer = "Int is a whole number|##|Double is a decimal number|##|Bool is a true or false value", options = "true or false value|##|decimal number|##|whole number"),
        Task(type = "MATCH_THE_ANSWERS", question = "'!='|##|'=='|##|'&&'|##|'||'", correctAnswer = "'!=' not equal to|##|'==' equal to|##|'&&' and|##|'||' or", options = "equal to|##|not equal to|##|or|##|and"),

    )
    tasks.forEach { db.taskDao().insertTask(it) }
}

suspend fun populateSection_Level(context: Context) = withContext(Dispatchers.IO){
    val db = AppDatabase.getDatabase(context)
    val sections = db.sectionDao().getAllSections()
    val levels = db.levelDao().getAllLevels()
    val section_levels = listOf(
        Section_Level(sectionId = sections[0].id, levelId = levels[0].id),
        Section_Level(sectionId = sections[0].id, levelId = levels[1].id),
        Section_Level(sectionId = sections[0].id, levelId = levels[3].id),
        Section_Level(sectionId = sections[0].id, levelId = levels[4].id),
        Section_Level(sectionId = sections[1].id, levelId = levels[0].id),
        Section_Level(sectionId = sections[1].id, levelId = levels[1].id),
        Section_Level(sectionId = sections[2].id, levelId = levels[0].id),
        Section_Level(sectionId = sections[2].id, levelId = levels[1].id),
        Section_Level(sectionId = sections[3].id, levelId = levels[0].id),
        Section_Level(sectionId = sections[4].id, levelId = levels[0].id),
        Section_Level(sectionId = sections[5].id, levelId = levels[0].id),
        Section_Level(sectionId = sections[5].id, levelId = levels[1].id),
        Section_Level(sectionId = sections[5].id, levelId = levels[2].id),
        Section_Level(sectionId = sections[3].id, levelId = levels[2].id)
    )
    section_levels.forEach { db.section_levelDao().insertSection_Level(it) }
}

suspend fun populateLevel_Task(context: Context) = withContext(Dispatchers.IO){
    val db = AppDatabase.getDatabase(context)
    val levels = db.levelDao().getAllLevels()
    val tasks = db.taskDao().getAllTasks()
    val level_tasks = listOf(
        Level_Task(levelId = levels[0].id, taskId = tasks[0].id, points = 0.0),
        Level_Task(levelId = levels[0].id, taskId = tasks[3].id, points = 0.0),
        Level_Task(levelId = levels[0].id, taskId = tasks[1].id, points = 0.0),
        Level_Task(levelId = levels[1].id, taskId = tasks[2].id, points = 0.0),
        Level_Task(levelId = levels[1].id, taskId = tasks[5].id, points = 0.0),
        Level_Task(levelId = levels[1].id, taskId = tasks[3].id, points = 0.0),
        Level_Task(levelId = levels[2].id, taskId = tasks[4].id, points = 0.0),
        Level_Task(levelId = levels[2].id, taskId = tasks[6].id, points = 0.0),
        Level_Task(levelId = levels[4].id, taskId = tasks[9].id, points = 0.0),
        Level_Task(levelId = levels[4].id, taskId = tasks[10].id, points = 0.0),
        Level_Task(levelId = levels[3].id, taskId = tasks[7].id, points = 0.0),
        Level_Task(levelId = levels[3].id, taskId = tasks[0].id, points = 0.0),
        Level_Task(levelId = levels[3].id, taskId = tasks[8].id, points = 0.0)
    )
    level_tasks.forEach { db.level_taskDao().insertLevel_Task(it) }
}

suspend fun populateStreak(context: Context) = withContext(Dispatchers.IO){
    val db = AppDatabase.getDatabase(context)
    val streak = Streak(currentStreak = 4, startDate = "2024-09-01", lastActiveDate = "2024-09-04")//YYYY-MM-DD
    db.streakDao().insertStreak(streak)
}