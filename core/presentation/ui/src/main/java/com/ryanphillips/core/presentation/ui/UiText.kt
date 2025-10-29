package com.ryanphillips.core.presentation.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/**
 * A custom interface that allows us to handle different strings
 * throughout the presentation layer (including ViewModels), regardless
 * of whether they are from resources or primitive strings.
 */
sealed interface UiText {
    // DynamicString wraps a primitive
    data class DynamicString(val value: String): UiText

    // StringResource holds the resId and any arguments for templated strings
    class StringResource(
        @StringRes val id: Int,
        val args: Array<Any> = arrayOf()
    ): UiText

    // A composable function that returns the primitive of either Dynamic/Resource
    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> stringResource(id = id, *args)
        }
    }

    // A non-composable function that returns the primitive of either Dynamic/Resource
    fun asString(context: Context): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> context.getString(id, *args)
        }
    }
}