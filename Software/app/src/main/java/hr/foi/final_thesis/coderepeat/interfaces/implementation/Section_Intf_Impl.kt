package hr.foi.final_thesis.coderepeat.interfaces.implementation

import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.entities.Section
import hr.foi.final_thesis.coderepeat.interfaces.ISection

class Section_Intf_Impl(private val context: Context): ISection {
    private val db = AppDatabase.getDatabase(context)

    override fun getAllSections(): List<Section> {
        return db.sectionDao().getAllSections()
    }

    override fun getSectionById(id: Int): Section? {
        return db.sectionDao().getSectionById(id)
    }

    override fun insertSection(section: Section): Long {
        return db.sectionDao().insertSection(section)
    }

    override fun updateSection(section: Section) {
        db.sectionDao().updateSection(section)
    }

    override fun deleteSection(section: Section) {
        db.sectionDao().deleteSection(section)
    }

    override fun deleteSectionById(id: Int) {
        db.sectionDao().deleteSectionById(id)
    }
}