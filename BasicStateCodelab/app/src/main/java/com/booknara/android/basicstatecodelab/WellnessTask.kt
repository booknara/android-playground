package com.booknara.android.basicstatecodelab

import androidx.compose.runtime.*

data class WellnessTask(val id: Int, val label: String, val initialChecked: Boolean = false) {
    var checked by mutableStateOf(initialChecked)
}
