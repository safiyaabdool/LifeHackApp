package com.example.lifehackapp

import android.R
import java.sql.Statement

data class Question(
    val statement: String,
    val isHack: Boolean,
    val explanation: R.string

)
