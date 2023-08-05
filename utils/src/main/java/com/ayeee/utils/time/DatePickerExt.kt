package com.ayeee.utils.time

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import java.util.Calendar
import java.util.Locale

fun Context.setupDatePicker(onDateSelected: (String) -> Unit): DatePickerDialog {
    val calendar = Calendar.getInstance(Locale.getDefault())

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePicker = DatePickerDialog(
        this, { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            val monthName = (mMonth).toMonthName()
            onDateSelected("$mDayOfMonth-$monthName-$mYear")
        }, year, month, day
    )
    datePicker.datePicker.minDate = calendar.time.time
    return datePicker
}