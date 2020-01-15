package com.angelsit.kudagofeed.data.dto.event

import java.text.SimpleDateFormat
import java.util.*

data class Date(
        val end: Long,
        val start: Long
) {
    companion object {
        fun getDisplayDates(start: Long, end: Long): String {
            val endDate = java.util.Date(end)
            val startDate = java.util.Date(start)

            val format = SimpleDateFormat("d MMMM", Locale("ru"))
            val startDisplayDate = format.format(startDate)
            val endDisplayDate = format.format(endDate)
            if (startDisplayDate == endDisplayDate) return startDisplayDate

            return " $startDisplayDate - $endDisplayDate"

            //return "7 октября"
        }
    }
}