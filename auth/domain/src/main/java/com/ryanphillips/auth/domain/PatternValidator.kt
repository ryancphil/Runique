package com.ryanphillips.auth.domain

interface PatternValidator {
    fun matches(value: String): Boolean
}