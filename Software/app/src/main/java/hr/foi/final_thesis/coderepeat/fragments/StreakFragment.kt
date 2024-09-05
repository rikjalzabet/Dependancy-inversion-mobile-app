package hr.foi.final_thesis.coderepeat.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.database.StreakDAO
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Streak_Intf_Impl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar


class StreakFragment : Fragment() {
    private lateinit var currentStreakNumberTitle: TextView
    private lateinit var calendarView: MaterialCalendarView
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
            withContext(Dispatchers.Main) {
                if (currentStreak > 0) {
                    highlightDatesOnCalendar()
                }
            }
        }
    }
    private fun highlightDatesOnCalendar(){
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val startDate=LocalDate.parse(startDate,formatter)
        val endLocalDate=LocalDate.parse(lastActiveDate,formatter)
        val colorDays= generateSequence(startDate) { it.plusDays(1) }
            .takeWhile { !it.isAfter(endLocalDate)}
            .map { CalendarDay.from(it.year,it.monthValue,it.dayOfMonth) }
            .toList()

        val decorator = object : DayViewDecorator {
            override fun shouldDecorate(day: CalendarDay): Boolean {
                return colorDays.contains(day)
            }

            override fun decorate(view: DayViewFacade) {
                view.addSpan(DotSpan(5f, Color.RED))
            }
        }
        calendarView.addDecorator(decorator)
    }
}