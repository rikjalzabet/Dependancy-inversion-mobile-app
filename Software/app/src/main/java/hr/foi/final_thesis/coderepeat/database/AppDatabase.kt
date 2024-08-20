package hr.foi.final_thesis.coderepeat.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Level_Task
import hr.foi.final_thesis.coderepeat.entities.Section
import hr.foi.final_thesis.coderepeat.entities.Section_Level
import hr.foi.final_thesis.coderepeat.entities.Streak
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.entities.Task_UserAnswer
import hr.foi.final_thesis.coderepeat.entities.UserAnswer

@Database(entities = [
        Level::class,
        Task::class,
        Streak::class,
        Section::class,
        Level_Task::class,
        Section_Level::class,
        Task_UserAnswer::class,
        UserAnswer::class
    ],
    version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sectionDao(): SectionDAO
    abstract fun levelDao(): LevelDAO
    abstract fun taskDao(): TaskDAO
    abstract fun level_taskDao(): Level_TaskDAO
    abstract fun streakDao(): StreakDAO
    abstract fun section_levelDao(): Section_LevelDAO
    abstract fun task_userAnswerDao(): Task_UserAnswerDAO
    abstract fun userAnswerDao(): UserAnswerDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase?=null

        fun getDatabase(context: Context): AppDatabase{
            /*return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }*/
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}