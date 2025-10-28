package com.ryanphillips.auth.data

import android.util.Patterns
import com.ryanphillips.auth.domain.PatternValidator

class EmailPatternValidator: PatternValidator {
    override fun matches(value: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }
}