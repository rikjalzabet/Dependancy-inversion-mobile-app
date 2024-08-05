package hr.foi.final_thesis.coderepeat.repository


import hr.foi.final_thesis.coderepeat.entities.Streak

interface StreakRepository {
    fun getCurrentStreak(id: Int): Streak?
    fun getAllStreaks(): List<Streak>
    fun insertStreak(streak: Streak)
    fun updateStreak(streak: Streak)
    fun deleteStreak(streak: Streak)
}