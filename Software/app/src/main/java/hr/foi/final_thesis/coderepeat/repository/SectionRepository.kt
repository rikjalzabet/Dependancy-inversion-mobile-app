package hr.foi.final_thesis.coderepeat.repository

import hr.foi.final_thesis.coderepeat.entities.Section

interface SectionRepository {
    fun getAllSections(): List<Section>
    fun getSectionById(id: Int): Section?
    fun insertSection(section: Section): Long

    fun updateSection(section: Section)
    fun deleteSection(section: Section)
    fun deleteSectionById(id: Int)

}