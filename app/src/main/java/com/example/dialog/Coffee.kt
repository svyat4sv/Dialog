package com.example.dialog

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Coffee (
    var type: String = "Americano",
    var isSugar: Boolean = false,
    var date: String = "",
    var time: String = "",
    var strength: Int = 0
): Parcelable