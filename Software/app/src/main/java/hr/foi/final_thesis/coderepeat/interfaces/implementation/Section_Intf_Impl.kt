package hr.foi.final_thesis.coderepeat.interfaces.implementation

import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.database.SectionDAO
import hr.foi.final_thesis.coderepeat.entities.Section
import hr.foi.final_thesis.coderepeat.interfaces.ISection

class Section_Intf_Impl(private val sectionDao: SectionDAO): ISection {
    override fun getAllSections(): List<Section> {
        return sectionDao.getAllSections()
    }
    override fun getSectionById(id: Int): Section? {
        return sectionDao.getSectionById(id)
    }
    override fun insertSection(section: Section): Long {
        return sectionDao.insertSection(section)
    }
    override fun updateSection(section: Section) {
        sectionDao.updateSection(section)
    }
    override fun deleteSection(section: Section) {
        sectionDao.deleteSection(section)
    }
    override fun deleteSectionById(id: Int) {
        sectionDao.deleteSectionById(id)
    }
}