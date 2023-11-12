package pl.edu.smcebi.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getCurrentDate(): String = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN))

const val DATE_PATTERN = "yyyy-MM-dd"