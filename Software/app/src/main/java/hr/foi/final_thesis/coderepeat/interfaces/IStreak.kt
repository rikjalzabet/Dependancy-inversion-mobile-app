package hr.foi.final_thesis.coderepeat.interfaces


import hr.foi.final_thesis.coderepeat.entities.Streak

interface IStreak {
    fun getCurrentStreak(id: Int): Streak?
    fun getStartStreakById(id: Int): Streak?
    fun getLastActiveStreakById(id: Int): Streak?
    fun getStreakById(id: Int): Streak?

    fun getAllStreaks(): List<Streak>
    fun insertStreak(streak: Streak)
    fun updateStreak(streak: Streak)
    fun deleteStreak(streak: Streak)
}