package com.example.listadecompras.framework.utils

import java.text.DecimalFormat

fun Float.toReal(): String{
    return try {
        val decimal = DecimalFormat("#######0.00")
        decimal.format(this)
    } catch (e: Exception) {
        "0.00"
    }
}