package hr.foi.final_thesis.coderepeat.repository

import hr.foi.final_thesis.coderepeat.entities.Level

interface LevelRepository {
    fun getAllLevels(): List<Level>
    fun getLevelById(id: Int): Level?
    fun insertLevel(level: Level): Long
    fun updateLevel(level: Level)
    fun deleteLevel(level: Level)
}