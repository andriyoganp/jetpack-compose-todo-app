package com.ayeee.utils.time

import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

infix fun Long.formattedDate(format: String): String {
    val date = Date(this)
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}

infix fun String.getLongFromDate(format: String): Long? {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.parse(this)?.time
}

fun getTimeMillisDifferenceWithDay(pastOrFutureInDays: Int = 0): Long? {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, pastOrFutureInDays)
    val format = "yy/MM/dd"
    val tomorrowDate = calendar.timeInMillis formattedDate format
    return tomorrowDate getLongFromDate format
}

fun Int.toMonthName(): String {
    return DateFormatSymbols(Locale.getDefault()).months[this]
}