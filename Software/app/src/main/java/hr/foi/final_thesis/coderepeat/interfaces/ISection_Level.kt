package hr.foi.final_thesis.coderepeat.interfaces

import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Section
import hr.foi.final_thesis.coderepeat.entities.Section_Level

interface ISection_Level {
    fun getSectionsForLevel(levelId: Int): List<Section>
    fun getLevelsForSection(sectionId: Int): List<Level>
    fun getAllSection_Levels(): List<Section_Level>
    fun insertSection_Level(section_level: Section_Level)
    fun updateSection_Level(section_level: Section_Level)
    fun deleteSection_Level(levelId: Int, sectionId: Int)
    fun deleteFromSection_Level(Section_Level: Section_Level)
}