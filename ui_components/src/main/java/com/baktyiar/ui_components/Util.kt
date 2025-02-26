package com.baktyiar.ui_components

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun formatDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}