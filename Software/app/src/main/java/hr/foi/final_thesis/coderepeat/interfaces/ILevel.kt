package hr.foi.final_thesis.coderepeat.interfaces

import hr.foi.final_thesis.coderepeat.entities.Level

interface ILevel {
    fun getAllLevels(): List<Level>
    fun getLevelById(id: Int): Level?
    fun insertLevel(level: Level): Long
    fun updateLevel(level: Level)
    fun deleteLevel(level: Level)
}