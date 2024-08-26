package hr.foi.final_thesis.coderepeat.interfaces.implementation

import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.database.StreakDAO
import hr.foi.final_thesis.coderepeat.entities.Streak
import hr.foi.final_thesis.coderepeat.interfaces.IStreak

class Streak_Intf_Impl(private val streakDao: StreakDAO): IStreak {
    override fun getCurrentStreak(id: Int): Int? {
        return streakDao.getCurrentStreak(id)
    }
    override fun getStartStreakById(id: Int): String? {
        return streakDao.getStartStreakById(id)
    }
    override fun getLastActiveStreakById(id: Int): String? {
        return streakDao.getLastActiveStreakById(id)
    }
    override fun getStreakById(id: Int): Streak? {
        return streakDao.getStreakById(id)
    }
    override fun getAllStreaks(): List<Streak> {
        return streakDao.getAllStreaks()
    }
    override fun insertStreak(streak: Streak) {
        streakDao.insertStreak(streak)
    }
    override fun updateStreak(streak: Streak) {
        streakDao.updateStreak(streak)
    }
    override fun deleteStreak(streak: Streak) {
        streakDao.deleteStreak(streak)
    }
}