package hr.foi.final_thesis.coderepeat.database

import android.content.Context
import hr.foi.final_thesis.coderepeat.entities.Section
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import populateData

object DatabaseManager {
    private var isDatabaseInitialized = false
    fun initializeDatabase(context: Context, onComplete: () -> Unit) {
        if (isDatabaseInitialized) {
            onComplete()
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(context)
            val levelDao = db.levelDao()
            val taskDao = db.taskDao()
            val sectionDao = db.sectionDao()
            val levelTaskDao = db.level_taskDao()
            val sectionLevelDao = db.section_levelDao()

            val levels = levelDao.getAllLevels()
            val tasks = taskDao.getAllTasks()
            val sections = sectionDao.getAllSections()

            if (levels.isEmpty() && tasks.isEmpty() && sections.isEmpty()) {
                populateData(context)
            }

            isDatabaseInitialized = true
            withContext(Dispatchers.Main) {
                onComplete()
            }
        }
    }
}
