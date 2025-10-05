package com.example.minimalistapp.util

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TimeTicker {

    // Emits formatted time string every second
    @RequiresApi(Build.VERSION_CODES.O)
    fun timeFlow(): Flow<String> = flow {
        val fmt = DateTimeFormatter.ofPattern("hh:mm:ss a")
        while (true) {
            emit(LocalDateTime.now().format(fmt))
            delay(1000)
        }
    }

    // Emit formatted date once (can be extended to update midnight)
    @RequiresApi(Build.VERSION_CODES.O)
    fun dateString(): String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy"))
}