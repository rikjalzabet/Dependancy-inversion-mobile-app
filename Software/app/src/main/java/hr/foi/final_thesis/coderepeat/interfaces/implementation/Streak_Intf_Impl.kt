package hr.foi.final_thesis.coderepeat.interfaces.implementation

import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.entities.Streak
import hr.foi.final_thesis.coderepeat.interfaces.IStreak

class Streak_Intf_Impl(private val context: Context): IStreak {
    private val db = AppDatabase.getDatabase(context)
    override fun getCurrentStreak(id: Int): Int? {
        return db.streakDao().getCurrentStreak(id)
    }
    override fun getStartStreakById(id: Int): String? {
        return db.streakDao().getStartStreakById(id)
    }
    override fun getLastActiveStreakById(id: Int): String? {
        return db.streakDao().getLastActiveStreakById(id)
    }
    override fun getStreakById(id: Int): Streak? {
        return db.streakDao().getStreakById(id)
    }
    override fun getAllStreaks(): List<Streak> {
        return db.streakDao().getAllStreaks()
    }
    override fun insertStreak(streak: Streak) {
        db.streakDao().insertStreak(streak)
    }
    override fun updateStreak(streak: Streak) {
        db.streakDao().updateStreak(streak)
    }
    override fun deleteStreak(streak: Streak) {
        db.streakDao().deleteStreak(streak)
    }
}