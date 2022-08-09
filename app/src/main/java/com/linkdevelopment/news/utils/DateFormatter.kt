package com.linkdevelopment.news.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    private val months = arrayOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

    fun format(date: String): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.ENGLISH)
        val time: Date = formatter.parse(date) ?: Date()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time.time
        return "${months[calendar.get(Calendar.MONTH)]} ${calendar.get(Calendar.DAY_OF_MONTH)}, ${calendar.get(Calendar.YEAR)}"
    }
}