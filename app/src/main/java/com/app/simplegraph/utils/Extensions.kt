package com.app.simplegraph

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun Date.toDateFormat(): String {
    val sdf = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    return sdf.format(this)
}

fun LocalDate.toDateFormat(): String {
    val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    return this.format(formatter)
}

const val DATE_FORMAT = "yyyy-MM-dd"
