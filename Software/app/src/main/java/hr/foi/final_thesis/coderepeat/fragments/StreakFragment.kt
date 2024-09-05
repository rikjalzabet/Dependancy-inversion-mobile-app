package hr.foi.final_thesis.coderepeat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.database.StreakDAO
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Streak_Intf_Impl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class StreakFragment : Fragment() {
    private lateinit var currentStreakNumberTitle: TextView
    private lateinit var calendarView: CalendarView
    private var currentStreak=0
    private var startDate: String = ""
    private var lastActiveDate: String = ""
    private lateinit var streakDao: StreakDAO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_streak, container, false)

        currentStreakNumberTitle = view.findViewById(R.id.fragment_streak_TV_NumberOfCurrentStreak)
        calendarView = view.findViewById(R.id.fragment_streak_CV_CurrentStreakCalendar)

        loadStreakData()

        if (currentStreak > 0) {
            highlightDatesOnCalendar()
        }

        return view
    }
    private fun loadStreakData() {
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(requireContext())
            streakDao = db.streakDao()
            val streak = Streak_Intf_Impl(streakDao).getAllStreaks()
            currentStreak = streak[0]?.currentStreak ?: 0
            currentStreakNumberTitle.text = "Current Streak: $currentStreak"
            startDate = streak[0].startDate
            lastActiveDate = streak[0].lastActiveDate
        }
    }
    private fun highlightDatesOnCalendar() {

    }
}