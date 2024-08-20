package hr.foi.final_thesis.coderepeat.interfaces.implementation

import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Section
import hr.foi.final_thesis.coderepeat.entities.Section_Level
import hr.foi.final_thesis.coderepeat.interfaces.ISection_Level

class Section_Level_Intf_Impl(private val context: Context) : ISection_Level {
    private val db = AppDatabase.getDatabase(context)

    override fun getSectionsForLevel(levelId: Int): List<Section> {
        return db.section_levelDao().getSectionsForLevel(levelId)
    }
    override fun getLevelsForSection(sectionId: Int): List<Level> {
        return db.section_levelDao().getLevelsForSection(sectionId)
    }
    override fun getAllSection_Levels(): List<Section_Level> {
        return db.section_levelDao().getAllSection_Levels()
    }
    override fun insertSection_Level(section_level: Section_Level) {
        db.section_levelDao().insertSection_Level(section_level)
    }
    override fun updateSection_Level(section_level: Section_Level) {
        db.section_levelDao().updateSection_Level(section_level)
    }
    override fun deleteSection_Level(levelId: Int, sectionId: Int) {
        db.section_levelDao().deleteSection_Level(levelId, sectionId)
    }
    override fun deleteFromSection_Level(Section_Level: Section_Level) {
        db.section_levelDao().deleteFromSection_Level(Section_Level)
    }
}