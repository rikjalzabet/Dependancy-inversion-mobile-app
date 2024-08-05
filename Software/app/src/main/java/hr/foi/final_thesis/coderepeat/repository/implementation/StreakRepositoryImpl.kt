package hr.foi.final_thesis.coderepeat.repository.implementation

import android.content.Context
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.entities.Streak
import hr.foi.final_thesis.coderepeat.repository.StreakRepository

class StreakRepositoryImpl(private val context: Context): StreakRepository {
    private val db = AppDatabase.getDatabase(context)
    override fun getCurrentStreak(id: Int): Streak? {
        return db.streakDao().getCurrentStreak(id)
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