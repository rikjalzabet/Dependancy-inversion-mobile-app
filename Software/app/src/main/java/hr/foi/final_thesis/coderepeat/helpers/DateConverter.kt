package hr.foi.final_thesis.coderepeat.helpers

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale

class DateConverter(){
    fun convertDateToString(date: Date): String {
        return date.toString()
    }
    fun convertLocalDateToString(date: LocalDate): String{
        return date.toString()
    }
    fun convertStringToDate(date: String): Date {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.parse(date)
    }
    fun subtractStringDates(date1: String, date2: String): Long {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val parsedDate1 = LocalDate.parse(date1, formatter)
        val parsedDate2 = LocalDate.parse(date2, formatter)

        return ChronoUnit.DAYS.between(parsedDate1, parsedDate2)
    }
    fun subtractDates(date1: LocalDate, date2: LocalDate): Long {
        return ChronoUnit.DAYS.between(date1, date2)
    }
}