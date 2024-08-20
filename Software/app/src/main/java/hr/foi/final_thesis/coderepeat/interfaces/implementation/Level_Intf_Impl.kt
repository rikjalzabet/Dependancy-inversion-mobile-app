package hr.foi.final_thesis.coderepeat.interfaces.implementation

import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.interfaces.ILevel

class Level_Intf_Impl(private val context: Context): ILevel {
    private val db = AppDatabase.getDatabase(context)

    override fun getAllLevels(): List<Level> {
        return db.levelDao().getAllLevels()
    }
    override fun getLevelById(id: Int): Level? {
        return db.levelDao().getLevelById(id)
    }
    override fun insertLevel(level: Level): Long {
        return db.levelDao().insertLevel(level)
    }
    override fun updateLevel(level: Level) {
        db.levelDao().updateLevel(level)
    }
    override fun deleteLevel(level: Level) {
        db.levelDao().deleteLevel(level)
    }
}