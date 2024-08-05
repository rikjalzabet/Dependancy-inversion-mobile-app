package hr.foi.final_thesis.coderepeat.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Streak
import hr.foi.final_thesis.coderepeat.entities.Task

@Database(entities = [Level::class, Task::class, Streak::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun levelDao(): LevelDAO
    abstract fun taskDao(): TaskDAO
    abstract fun streakDao(): StreakDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase?=null

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}